// 文件路径: user-service/src/main/java/com/cloudcart/userservice/config/WebConfig.java

package com.sp25.cs5207el06.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 1. 对所有以 /api/ 开头的路径生效
                        .allowedOriginPatterns("https://*.nullpointerexceptions.com", "https://*.firebaseapp.com", "https://*.web.app", "https://*.run.app") // 2. 允许的前端源地址
//                        .allowedOrigins("*") // 2. 允许的前端源地址
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 3. 允许的请求方法
                        .allowedHeaders("*") // 4. 允许所有的请求头
                        .exposedHeaders("*") // 4. 允许暴露的响应头
                        .allowPrivateNetwork(true) // 4. 允许私有网络访问
                        .allowCredentials(true); // 5. 是否允许发送 Cookie
            }
        };
    }
}