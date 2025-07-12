package com.sp25.cs5207el06.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.sp25.cs5207el06.entity") // 扫描实体类所在的包
//@EnableJpaRepositories(basePackages = "com.sp25.cs5207el06") // 扫描仓库接口所在的包
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
