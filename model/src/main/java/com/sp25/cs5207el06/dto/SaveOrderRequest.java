package com.sp25.cs5207el06.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/7/3 10:48 PM
 * @Version 1.0
 */
@Data
public class SaveOrderRequest {
    private Long productId;
    private String productName;
    private PaymentDetails paymentDetails;
    @Data
    public static class PaymentDetails {
        @JsonProperty(value = "card")
        private String cardNum;

        @JsonProperty(value = "cvv")
        private String cardCvv;

        @JsonProperty(value = "expiry")
        private String cardExpire; // Format: MM/yy
    }
}
