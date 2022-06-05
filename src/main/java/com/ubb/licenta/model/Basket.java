package com.ubb.licenta.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "basket")
@Getter
@Setter
@ToString
public class Basket extends BaseEntity<Long> implements Serializable {
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn
    private List<Product> products;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private User client;

    public Basket(Long aLong, List<Product> products, User client) {
        super(aLong);
        this.products = products;
        this.client = client;
    }

    public Basket() {
    }
}
