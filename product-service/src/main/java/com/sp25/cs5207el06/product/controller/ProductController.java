package com.sp25.cs5207el06.product.controller;

import com.sp25.cs5207el06.entity.Product;
import com.sp25.cs5207el06.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 10:15 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add methods to handle product-related requests here
    // For example, you can add a method to list all products
     @GetMapping("")
     public List<Product> listAllProducts() {
         return productService.listAllProducts();
     }

     // You can also add methods to find a product by ID or other criteria
    @GetMapping("/{productId}")
    public Product findProductById(@PathVariable Long productId) {
        return productService.findProductById(productId);
    }
}
