package com.sp25.cs5207el06.order.controller;

import com.sp25.cs5207el06.Constants;
import com.sp25.cs5207el06.dto.SaveOrderRequest;
import com.sp25.cs5207el06.entity.Orders;
import com.sp25.cs5207el06.entity.Product;
import com.sp25.cs5207el06.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 10:15 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${services.product.url:http://localhost:5300/product}")
    private String productServiceUrl;

    @GetMapping("/customer")
    public List<Orders> findOrdersByCustomerId(HttpServletRequest request) {
        Long customerId = getCustomerIdFromRequest(request);
        return orderService.findOrdersByCustomerId(customerId);
    }

    // use POST method to save an order with parameter productId and paymentDetails
    @PostMapping("")
    public Orders saveOrder(@RequestBody SaveOrderRequest saveOrderRequest, HttpServletRequest request) {
        // Assuming you have stored the customer ID in Redis after login
        Long customerId = getCustomerIdFromRequest(request);
        Long productId = saveOrderRequest.getProductId();

        // get product information from product service
        /*String resp = HttpUtil.get("http://localhost:5300/product/%s".formatted(productId));
        if (!StringUtils.hasText(resp)) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
        JSONObject product = JSONUtil.parseObj(resp);
        String productName = product.getStr("name");
        Long price = product.getLong("price");*/

        // 使用注入的 WebClient 发起非阻塞调用
        // Micrometer 会自动拦截这个请求，并注入追踪头 (例如 B3 Headers)
        Mono<Product> productMono = this.webClientBuilder
                .baseUrl(productServiceUrl)
                .build()
                .get()
                .uri("/{id}", productId) // 使用 URI 模板
                .retrieve() // 获取响应
                .bodyToMono(Product.class); // 将响应体转换为 Product 的 Mono

        Product product = productMono.block();// 阻塞等待响应
        String productName = product.getName();
        Long price = product.getPrice();

        Orders order = new Orders()
                .setOrderSeq(RandomUtil.randomNumbers(16))
                .setCustomerId(customerId)
                .setProductId(productId)
                .setProductName(productName)
                .setUnitPrice(price)
                .setQuantity(1)
                .setCardNum(saveOrderRequest.getPaymentDetails().getCardNum())
                .setCardExpire(saveOrderRequest.getPaymentDetails().getCardExpire())
                .setCardCvv(saveOrderRequest.getPaymentDetails().getCardCvv());
        return orderService.saveOrder(order);
    }

    /**
     * get token from http request
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String authorization = StrUtil.trimToEmpty(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (!StringUtils.hasText(authorization)) {
            throw new RuntimeException("Authorization header is missing");
        }
        return authorization.replace("Bearer ", "").trim();
    }

    /**
     * get user id from http request
     */
    private Long getCustomerIdFromRequest(HttpServletRequest request) {
        String authToken = getTokenFromRequest(request);
        String redisKey = Constants.AUTH_TOKEN_REDIS_KEY_PREFIX + authToken;
        Optional<Object> customerIdOptional = Optional.ofNullable(redisTemplate.opsForValue().get(redisKey));
        if (customerIdOptional.isEmpty()) {
            throw new RuntimeException("Could not retrieve customer ID for unauthenticated user");
        }
        // Assuming you have stored the customer ID in Redis after login
        return Long.parseLong(customerIdOptional.get().toString()); // Default to 1 if not found
    }
}
