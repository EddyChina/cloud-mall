package com.sp25.cs5207el06.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.sp25.cs5207el06.entity") // 扫描实体类所在的包
//@EnableJpaRepositories(basePackages = "com.sp25.cs5207el06") // 扫描仓库接口所在的包
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
