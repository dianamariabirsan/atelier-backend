package com.ubb.licenta.dto;

import com.ubb.licenta.model.User;
import com.ubb.licenta.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends BaseDto {
    private List<ProductDto> products;
    private User client;
    private Long clientId;
    private String shippingAddress;
    private Long dateOfOrderAsTs;
    private Status status;
}
