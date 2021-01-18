package com.parkhomenko.fauna.service;

import com.parkhomenko.fauna.model.Product;
import com.parkhomenko.fauna.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() throws ExecutionException, InterruptedException {
        return productRepository.allProducts();
    }
}
