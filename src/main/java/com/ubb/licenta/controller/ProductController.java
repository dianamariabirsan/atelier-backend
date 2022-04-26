package com.ubb.licenta.controller;

import com.ubb.licenta.converter.BaseConverter;
import com.ubb.licenta.dto.ProductDto;
import com.ubb.licenta.model.Product;
import com.ubb.licenta.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/product") //localhost:8080/product
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private BaseConverter<Product, ProductDto> converter;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Integer productId) {
        //localhost:8080/product/1
        Product product = service.getProductById(productId);
        ProductDto productDto = converter.convertModelToDto(product);
        if (productDto == null) {
            log.info("Unable to find any product with id: " + productId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Returning product with id: " + productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/products")
    //localhost:8080/product/products
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = service.getProducts();
        List<ProductDto> productsDto = converter.convertModelsToDtos(products);
        if (productsDto == null) {
            log.info("Unable to find any products");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        Product product = converter.convertDtoToModel(productDto);
        Product saved = service.save(product);
        if (saved == null) {
            log.info("Unable to save product");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
        Product product = converter.convertDtoToModel(productDto);
        Product saved = service.update(product);
        if (saved == null) {
            log.info("Unable to update product");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long productId) {
        //localhost:8080/product/1
        service.delete(productId);
        log.info("Returning product with id: " + productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{ProductByString}")
    public ResponseEntity<ProductDto> filterProductByString(@PathVariable("ProductByString") String productByString) {
        Product product = (Product) service.filterProductByString(productByString);
        ProductDto productDto = converter.convertModelToDto(product);
        if (productDto == null) {
            log.info("Unable to find any product with the given type name: " + productByString);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Returning product with name type: " + productByString);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/{ProductByPrice}")
    public ResponseEntity<ProductDto> filterProductByPrice(@PathVariable("ProductByPrice") int productByPrice) {
        Product product = (Product) service.filterProductsByPrice(productByPrice);
        ProductDto productDto = converter.convertModelToDto(product);
        if (productDto == null) {
            log.info("Unable to find any product with the given price: " + productByPrice);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Returning product with the given price: " + productByPrice);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/{ProductByPriceSorted}")
    public ResponseEntity<ProductDto> filterProductByPriceSorted(@PathVariable("ProductByPriceSorted") int productByPriceSorted) {
        Product product = (Product) service.filterProductsByPriceSorted(productByPriceSorted);
        ProductDto productDto = converter.convertModelToDto(product);
        if (productDto == null) {
            log.info("Unable to find any product: " + productByPriceSorted);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Returning product: " + productByPriceSorted);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
    
}