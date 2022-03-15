package com.ubb.licenta.repository;

import com.ubb.licenta.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}