package com.sp25.cs5207el06.product.repo;

import com.sp25.cs5207el06.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 10:13 PM
 * @Version 1.0
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
