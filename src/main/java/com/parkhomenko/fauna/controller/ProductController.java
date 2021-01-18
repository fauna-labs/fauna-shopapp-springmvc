package com.parkhomenko.fauna.controller;

import com.parkhomenko.fauna.model.Product;
import com.parkhomenko.fauna.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public List<Product> getProducts() throws ExecutionException, InterruptedException {
        return productService.getProducts();
    }
}
