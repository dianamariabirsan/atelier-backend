package com.ubb.licenta.dto;

import com.ubb.licenta.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto extends BaseDto implements Serializable {
    private List<Product> products;
    private Long clientId;
}
