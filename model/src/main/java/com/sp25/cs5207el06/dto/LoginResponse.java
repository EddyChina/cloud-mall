package com.sp25.cs5207el06.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 9:36 PM
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class LoginResponse {
    private Boolean success = true;
    private String token;
    private User user;

    @Data
    @Accessors(chain = true)
    public static class User {
        private String name;
    }
}
