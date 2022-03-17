package com.ubb.licenta.service;

import com.ubb.licenta.model.Client;
import com.ubb.licenta.repository.ProductRepository;
import com.ubb.licenta.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Long productId) {
        repository.deleteById(productId);
    }

    @Override
    public Product getProductById(long id) {
        return repository.findProductById(id);
    }
}

