package com.ubb.licenta.service;

import com.ubb.licenta.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> getProducts();

    Product update(Product product);

    void delete(Long productId);

    Product getProductById(long id);

    List<Product> filterProductsByPriceSorted(int minPrice, int maxPrice);

    List<Product> filterBy(String filter, Double minPrice, Double maxPrice, Boolean sortAscending);

}