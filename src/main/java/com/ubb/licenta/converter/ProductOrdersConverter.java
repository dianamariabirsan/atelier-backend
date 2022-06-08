package com.ubb.licenta.converter;

import com.ubb.licenta.dto.ProductDto;
import com.ubb.licenta.model.Product;
import com.ubb.licenta.model.ProductOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductOrdersConverter extends BaseConverter<ProductOrder, ProductDto> {

    private static final Logger log = LoggerFactory.getLogger(ProductOrdersConverter.class);

    @Override
    public ProductOrder convertDtoToModel(ProductDto dto) {
        Product product = new Product(dto.getId(), dto.getType(), dto.getDescription(),
                dto.getPrice(), dto.getImage());
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setQuantity(dto.getOrderQuantity());
        return productOrder;
    }

    @Override
    public ProductDto convertModelToDto(ProductOrder productOrder) {
        Product product = productOrder.getProduct();
        ProductDto productDto = new ProductDto(product.getType(), product.getDescription(),
                product.getPrice(), product.getImage(), productOrder.getQuantity());
        productDto.setId(product.getId());
        return productDto;
    }
}
