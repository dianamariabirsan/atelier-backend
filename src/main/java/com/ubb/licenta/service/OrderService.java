package com.ubb.licenta.service;

import com.ubb.licenta.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    List<Order> getOrders();

    Order update(Order order);

    void delete(Long orderId);

    Order getOrderById(long id);

    List<Order> filterBy(String orderStatus, Long dateOfLastOrderAsTs, Boolean sortDescending);
}
