package com.ubb.licenta.controller;

import com.ubb.licenta.converter.BaseConverter;
import com.ubb.licenta.dto.OrderDto;
import com.ubb.licenta.dto.ProductDto;
import com.ubb.licenta.model.Order;
import com.ubb.licenta.model.ProductOrder;
import com.ubb.licenta.service.OrderService;
import com.ubb.licenta.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/order") //localhost:8080/order
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BaseConverter<Order, OrderDto> orderConverter;

    @Autowired
    private BaseConverter<ProductOrder, ProductDto> productOrdersConverter;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Integer orderId) {
        //localhost:8080/order/1
        Order order = orderService.getOrderById(orderId);
        OrderDto orderDto = orderConverter.convertModelToDto(order);
        orderDto.setProducts(productOrdersConverter.convertModelsToDtos(order.getProductOrders()));

        if (orderDto == null) {
            log.info("Unable to find any order with id: " + orderId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Returning order with id: " + orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/orders")
    //localhost:8080/order/orders
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.getOrders();
        List<OrderDto> ordersDto = orderConverter.convertModelsToDtos(orders);

        // set on ordersDto the corresponding productsDtos
        for (int i=0; i<ordersDto.size(); i++) {
            ordersDto.get(i).setProducts(productOrdersConverter.convertModelsToDtos(orders.get(i).getProductOrders()));
        }

        if (ordersDto == null) {
            log.info("Unable to find any orders");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
        Order order = orderConverter.convertDtoToModel(orderDto);
        order.setProductOrders(productOrdersConverter.convertDtosToModels(orderDto.getProducts()));
        order.setClient(userService.getClientById(orderDto.getClientId()));

        Order saved = orderService.save(order);
        if (saved == null) {
            log.info("Unable to save order");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto) {
        Order order = orderConverter.convertDtoToModel(orderDto);
        order.setProductOrders(productOrdersConverter.convertDtosToModels(orderDto.getProducts()));
        order.setClient(userService.getClientById(orderDto.getClientId()));

        Order saved = orderService.update(order);
        if (saved == null) {
            log.info("Unable to update order");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId) {
        //localhost:8080/order/1
        orderService.delete(orderId);
        log.info("Returning order with id: " + orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterBy(
            @RequestParam(name = "orderStatus", required = false) String orderStatus,
            @RequestParam(name = "dateOfLastOrder", required = false) Long dateOfLastOrderAsTs,
            @RequestParam(name = "sortDesc", required = false) Boolean sortDescending
    ) {
        List<Order> orders = orderService.filterBy(orderStatus, sortDescending);
        List<OrderDto> orderDtos = orderConverter.convertModelsToDtos(orders);

        // set on ordersDto the corresponding productsDtos
        for (int i=0; i<orderDtos.size(); i++) {
            orderDtos.get(i).setProducts(productOrdersConverter.convertModelsToDtos(orders.get(i).getProductOrders()));
        }

        log.info("Filter: orderStatus = " + orderStatus + ", dateOfLastOrderAsTs = " + dateOfLastOrderAsTs + ", sortDescending = " + sortDescending);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }
}