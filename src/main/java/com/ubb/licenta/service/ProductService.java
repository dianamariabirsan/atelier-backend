package com.ubb.licenta.service;

import com.ubb.licenta.model.Client;
import com.ubb.licenta.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> getProducts();

    Product update(Product product);

    void delete(Long productId);

    Product getProductById(long id);
}