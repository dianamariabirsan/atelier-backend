package com.ubb.licenta.repository;

import com.ubb.licenta.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findAllByProductId(Long productId);

    void deleteProductOrderById(Long id);
}
