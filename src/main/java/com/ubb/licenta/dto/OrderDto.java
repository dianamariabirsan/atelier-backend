package com.ubb.licenta.dto;

import com.ubb.licenta.model.Client;
import com.ubb.licenta.model.Product;
import com.ubb.licenta.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends BaseDto {
    private List<Product> products;
    private Client client;
    private String shippingAddress;
    private Long dateOfOrderAsTs;
    private Status status;
}
