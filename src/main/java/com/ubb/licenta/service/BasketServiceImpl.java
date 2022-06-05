package com.ubb.licenta.service;

import com.ubb.licenta.model.Basket;
import com.ubb.licenta.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository repository;

    @Override
    public Basket save(Basket basket) {
        return repository.save(basket);
    }

    @Override
    public Basket update(Basket basket) {
        return repository.save(basket);
    }

    @Override
    public void delete(Long basketId) {
        repository.deleteById(basketId);
    }

    @Override
    public Basket getByClientId(Long clientId) {
        return repository.findBasketByClient_Id(clientId);
    }
}
