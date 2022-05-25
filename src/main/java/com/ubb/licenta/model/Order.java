package com.ubb.licenta.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order extends BaseEntity<Long> implements Comparable<Order> {
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn
    private List<Product> products;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private Client client;

    private String shippingAddress;

    private Long dateOfOrderAsTs;

    private Status status;

    public Order(Long aLong, List<Product> products, Client client, String shippingAddress, Long dateOfOrderAsTs, Status status) {
        super(aLong);
        this.products = products;
        this.client = client;
        this.shippingAddress = shippingAddress;
        this.dateOfOrderAsTs = dateOfOrderAsTs;
        this.status = status;
    }

    public Order() {
    }

    @Override
    public int compareTo(Order o) {
        return dateOfOrderAsTs.compareTo(o.dateOfOrderAsTs);
    }
}
