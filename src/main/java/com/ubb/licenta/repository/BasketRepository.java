package com.ubb.licenta.repository;

import com.ubb.licenta.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findBasketByClient_Id(Long clientId);
}