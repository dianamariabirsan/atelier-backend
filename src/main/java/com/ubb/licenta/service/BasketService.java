package com.ubb.licenta.service;

import com.ubb.licenta.model.Basket;

public interface BasketService {
    Basket save(Basket basket);

    Basket update(Basket basket);

    void delete(Long basketId);

    Basket getByClientId(Long clientId);
}
