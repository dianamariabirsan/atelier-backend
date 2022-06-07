package com.ubb.licenta.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
public class Product extends BaseEntity<Long> implements Comparable<Product> {
    private String type;
    private String description;
    private Double price;
    private String image;

    public Product(Long aLong, String type, String description, Double price, String image) {
        super(aLong);
        this.type = type;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }

    @Override
    public int compareTo(Product o) {
        return price.compareTo(o.getPrice());
    }
}
