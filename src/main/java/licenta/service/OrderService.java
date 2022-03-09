package licenta.service;

import licenta.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> getOrders();

    Order update(Order order);

    void delete(Long orderId);
}
