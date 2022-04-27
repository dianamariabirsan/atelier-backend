package com.ubb.licenta.converter;

import com.ubb.licenta.dto.ProductDto;
import com.ubb.licenta.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter extends BaseConverter<Product, ProductDto> {

    private static final Logger log = LoggerFactory.getLogger(ProductConverter.class);

    @Override
    public Product convertDtoToModel(ProductDto dto) {
        Product product = new Product(dto.getId(), dto.getType(), dto.getDescription(), dto.getPrice(), dto.getImage());
        return product;
    }

    @Override
    public ProductDto convertModelToDto(Product product) {
        ProductDto productDto = new ProductDto(product.getType(), product.getDescription(), product.getPrice(), product.getImage());
        productDto.setId(productDto.getId());
        return productDto;
    }
}
