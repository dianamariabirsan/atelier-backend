package com.ubb.licenta.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@ToString
public class Order extends BaseEntity<Long> implements Serializable {
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn
    private List<Product> products;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private Client client;

    private Status status;
}
