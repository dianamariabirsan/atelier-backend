package com.ubb.licenta.converter;

import com.ubb.licenta.dto.OrderDto;
import com.ubb.licenta.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends BaseConverter<Order, OrderDto> {

    private static final Logger log = LoggerFactory.getLogger(OrderConverter.class);
    @Override
    public Order convertDtoToModel(OrderDto dto) {
        Order order = new Order(dto.getId(), dto.getProducts(),
                dto.getClient(), dto.getShippingAddress(), dto.getDateOfOrderAsTs(), dto.getStatus());
        return order;
    }

    @Override
    public OrderDto convertModelToDto(Order order) {
        OrderDto orderDto = new OrderDto(order.getProducts(),
        order.getClient(), order.getShippingAddress(), order.getDateOfOrderAsTs(), order.getStatus());
        orderDto.setId(order.getId());
        return orderDto;
    }
}
