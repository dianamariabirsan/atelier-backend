package com.ubb.licenta.converter;

import com.ubb.licenta.dto.OrderDto;
import com.ubb.licenta.model.Order;
import com.ubb.licenta.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends BaseConverter<Order, OrderDto> {

    private static final Logger log = LoggerFactory.getLogger(OrderConverter.class);

    @Override
    public Order convertDtoToModel(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        User client = new User();
        client.setId(dto.getClientId());
        order.setClient(client);
        order.setShippingAddress(dto.getShippingAddress());
        order.setDateOfOrderAsTs(dto.getDateOfOrderAsTs());
        order.setStatus(dto.getStatus());
        return order;
    }

    @Override
    public OrderDto convertModelToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setClient(order.getClient());
        orderDto.setShippingAddress(order.getShippingAddress());
        orderDto.setDateOfOrderAsTs(order.getDateOfOrderAsTs());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }
}
