package com.test.test.service;

import com.test.test.model.Product;
import com.test.test.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getById(String id) {
        return productRepository.findById(id);
    }

    public Optional<Product> update(String id, Product newProduct) {
        return productRepository.findById(id).map(p -> {
            p.setName(newProduct.getName());
            p.setPrice(newProduct.getPrice());
            return productRepository.save(p);
        });
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
