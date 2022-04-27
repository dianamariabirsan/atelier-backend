package com.ubb.licenta.service;

import com.ubb.licenta.repository.ProductRepository;
import com.ubb.licenta.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Long productId) {
        repository.deleteById(productId);
    }

    @Override
    public Product getProductById(long id) {
        return repository.findProductById(id);
    }

    @Override
    public List<Product> filterProductsByPriceSorted(int minPrice, int maxPrice) {
        List<Product> filteredProducts = repository.findAll().stream()
                .filter((product) -> minPrice <= product.getPrice() && product.getPrice() <= maxPrice)
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
        return filteredProducts;
    }

    @Override
    public List<Product> filterBy(String filter, Double minPrice, Double maxPrice, Boolean sortAscending) {
        // daca parametri sunt setati la null atunci ei nu au fost prezenti in requestul de la frontend
        Predicate<Product> filterByAnyString = x -> false;

        if (filter != null) {
            filterByAnyString = filterByAnyString
                    .or(x -> x.getType() != null && x.getType().contains(filter))
                    .or(x -> x.getDescription() != null && x.getDescription().contains(filter));
        }

        Predicate<Product> filterByPrices = x -> false;

        if (minPrice == null) {
            minPrice = 0.0;
        }
        if (maxPrice == null) {
            maxPrice = Double.MAX_VALUE;
        }

        Double finalMinPrice = minPrice;
        Double finalMaxPrice = maxPrice;
        filterByPrices = filterByPrices
                .or(x -> finalMinPrice <= x.getPrice() && x.getPrice() <= finalMaxPrice);

        Predicate<Product> mainPredicate = filterByAnyString.and(filterByPrices);

        List<Product> filteredProducts =  repository.findAll()
                .stream()
                .filter(mainPredicate)
                .collect(Collectors.toList());

        if (sortAscending == null) {
            sortAscending = true;
        }

        if (sortAscending) {
            Collections.sort(filteredProducts);
        } else {
            Collections.sort(filteredProducts, Collections.reverseOrder());
        }

        return filteredProducts;
    }
}

