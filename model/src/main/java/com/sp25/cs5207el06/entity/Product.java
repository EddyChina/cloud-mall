package com.sp25.cs5207el06.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 10:08 PM
 * @Version 1.0
 */
@Table
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    private String imageUrl;
}
