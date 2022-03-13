package licenta.converter;

import licenta.dto.OrderDto;
import licenta.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends BaseConverter<Order, OrderDto> {

    private static final Logger log = LoggerFactory.getLogger(OrderConverter.class);
    @Override
    public Order convertDtoToModel(OrderDto dto) {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public OrderDto convertModelToDto(Order order) {
        OrderDto orderDto = new OrderDto(order.getProducts(),
        order.getClient(), order.getStatus());
        orderDto.setId(orderDto.getId());
        return orderDto;
    }
}
