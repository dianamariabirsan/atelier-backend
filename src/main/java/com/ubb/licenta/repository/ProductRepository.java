package com.ubb.licenta.repository;

import com.ubb.licenta.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(long id);
}
