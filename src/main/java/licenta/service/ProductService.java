package licenta.service;

import licenta.model.Client;
import licenta.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> getProducts();

    Product update(Product product);

    void delete(Long productId);
}