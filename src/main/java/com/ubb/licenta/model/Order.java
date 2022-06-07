package com.ubb.licenta.model;

import com.ubb.licenta.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order extends BaseEntity<Long> implements Comparable<Order> {
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn
    private List<ProductOrder> productOrders;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private User client;

    private String shippingAddress;

    private Long dateOfOrderAsTs;

    private Status status;

    public Order(Long aLong, List<ProductOrder> productOrders, User client, String shippingAddress, Long dateOfOrderAsTs, Status status) {
        super(aLong);
        this.productOrders = productOrders;
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
