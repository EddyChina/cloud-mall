package com.sp25.cs5207el06.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 6:34 PM
 * @Version 1.0
 */
@Entity
@Data
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String loginName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}