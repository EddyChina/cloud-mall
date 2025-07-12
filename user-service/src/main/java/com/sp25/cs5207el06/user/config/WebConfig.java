// 文件路径: user-service/src/main/java/com/cloudcart/userservice/config/WebConfig.java

package com.sp25.cs5207el06.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置连接工厂
        template.setConnectionFactory(connectionFactory);

        // 创建 JSON 序列化工具
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

        // 设置 Key 的序列化器为 StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // 设置 Value 的序列化器为 GenericJackson2JsonRedisSerializer
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        // 初始化 RedisTemplate
        template.afterPropertiesSet();

        return template;
    }
}