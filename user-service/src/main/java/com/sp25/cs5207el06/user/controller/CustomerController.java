package com.sp25.cs5207el06.user.controller;

import com.sp25.cs5207el06.Constants;
import com.sp25.cs5207el06.dto.LoginRequest;
import com.sp25.cs5207el06.dto.LoginResponse;
import com.sp25.cs5207el06.entity.Customer;
import com.sp25.cs5207el06.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 6:46 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    // 这里可以实现用户相关的控制器逻辑
    // 比如处理用户注册、登录等请求
    // 可以注入 CustomerService 并调用其方法来处理业务逻辑
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/{loginName}")
    public ResponseEntity<Customer> findByLoginName(@PathVariable String loginName) {
        // 这里可以调用 CustomerService 的方法来获取用户信息
         Customer customer = customerService.findByLoginName(loginName);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        // 这里可以调用 CustomerService 的方法来处理登录逻辑
        Customer customer = customerService.findByLoginName(loginRequest.getUsername());
        if (customer == null || !customer.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // 假设我们有一个方法生成 JWT token
        String token = UUID.randomUUID().toString();

        // 这里可以将 token 存储在 Redis 或其他存储中，以便后续验证
        // 例如：
        redisTemplate.opsForValue().set(Constants.AUTH_TOKEN_REDIS_KEY_PREFIX + token, customer.getId(), 1, TimeUnit.HOURS);

        return new LoginResponse()
                .setSuccess(true)
                .setToken(token)
                .setUser(new LoginResponse.User().setName(customer.getCustomerName()));

    }
}
