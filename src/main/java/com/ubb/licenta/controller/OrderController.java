package com.ubb.licenta.controller;

import com.ubb.licenta.converter.BaseConverter;
import com.ubb.licenta.dto.OrderDto;
import com.ubb.licenta.dto.ProductDto;
import com.ubb.licenta.model.Order;
import com.ubb.licenta.model.Product;
import com.ubb.licenta.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/order") //localhost:8080/order
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private BaseConverter<Order, OrderDto> converter;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Integer orderId) {
        //localhost:8080/order/1
        Order order = service.getOrderById(orderId);
        OrderDto orderDto = converter.convertModelToDto(order);
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
        List<Order> orders = service.getOrders();
        List<OrderDto> ordersDto = converter.convertModelsToDtos(orders);
        if (ordersDto == null) {
            log.info("Unable to find any orders");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
        Order order = converter.convertDtoToModel(orderDto);
        Order saved = service.save(order);
        if (saved == null) {
            log.info("Unable to save order");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto) {
        Order order = converter.convertDtoToModel(orderDto);
        Order saved = service.update(order);
        if (saved == null) {
            log.info("Unable to update order");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId) {
        //localhost:8080/order/1
        service.delete(orderId);
        log.info("Returning order with id: " + orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterBy(
            @RequestParam(name = "orderStatus", required = false) String orderStatus,
            @RequestParam(name = "dateOfLastOrder", required = false) Long dateOfLastOrderAsTs,
            @RequestParam(name = "sortDesc", required = false) Boolean sortDescending
    ) {
        List<Order> orders = service.filterBy(orderStatus, dateOfLastOrderAsTs, sortDescending);
        List<OrderDto> orderDtos = converter.convertModelsToDtos(orders);
        log.info("Filter: orderStatus = " + orderStatus + ", dateOfLastOrderAsTs = " + dateOfLastOrderAsTs + ", sortDescending = " + sortDescending);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }
}