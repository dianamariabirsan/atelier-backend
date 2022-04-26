package com.ubb.licenta.service;

import com.ubb.licenta.repository.ProductRepository;
import com.ubb.licenta.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<Product> filterProductByString(String s) {
        return repository.findAll().stream()
                .filter((product) -> product.getType().contains(s))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filterProductsByPrice(int price) {
        Set<Product> minPrice = repository.findAll().stream().filter(product -> product.getPrice()<price).collect(Collectors.toSet());
        Set <Product> maxPrice = repository.findAll().stream().filter(product -> product.getPrice()>=price).collect(Collectors.toSet());
        List<Product> filteredProducts = new ArrayList<>();
        filteredProducts.addAll(minPrice);
        filteredProducts.addAll(maxPrice);
        return filteredProducts;
    }

    @Override
    public List<Product> filterProductsByPriceSorted(int price) {
        List <Product> minPrice = repository.findAll().stream()
                .filter((product) -> product.getPrice() < price)
                .sorted(Comparator.comparing(Product::getType))
                .collect(Collectors.toList());
        List <Product> maxPrice = repository.findAll().stream()
                .filter(product -> product.getPrice() >= price)
                .sorted((Product b1, Product b2) -> b2.getPrice() - b1.getPrice())
                .collect(Collectors.toList());

        List<Product> filteredProducts = new ArrayList<>();
        filteredProducts.addAll(minPrice);
        filteredProducts.addAll(maxPrice);
        return filteredProducts;
    }
}

