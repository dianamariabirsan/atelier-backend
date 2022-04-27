package com.ubb.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseDto {
    private String type;
    private String description;
    private Double price;
    private String image;
}
