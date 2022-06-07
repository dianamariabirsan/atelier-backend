package com.ubb.licenta.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "productorders")
@Getter
@Setter
@ToString
public class ProductOrder extends BaseEntity<Long> implements Serializable {
    @ManyToOne
    @JoinColumn
    private Product product;
    private int quantity;
}
