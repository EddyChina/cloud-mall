package com.sp25.cs5207el06.product.service;

import com.sp25.cs5207el06.entity.Product;
import com.sp25.cs5207el06.product.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 10:14 PM
 * @Version 1.0
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    // list all products
    public List<Product> listAllProducts() {
        return productRepo.findAll();
    }

    // find product by ID
    public Product findProductById(Long productId) {
        return productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }
}
