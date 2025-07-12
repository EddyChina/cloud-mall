package com.sp25.cs5207el06.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/29 10:32 PM
 * @Version 1.0
 */
@Entity
@Table
@Data
@Accessors(chain = true)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderSeq;

    private Long customerId;

    private Long productId;
    private String productName;
    private Long unitPrice;

    private Integer quantity;

    @Column(name = "order_status", insertable = false)
    private String status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cardNum;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cardCvv;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cardExpire;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;
}
