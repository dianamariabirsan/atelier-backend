package com.ubb.licenta.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="products")
@Getter
@Setter
@ToString
public class Product extends BaseEntity<Long> implements Serializable {
    private String type;
    private int price;
    private String image;

    public Product(Long aLong, String type, int price, String image) {
        super(aLong);
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }
}
