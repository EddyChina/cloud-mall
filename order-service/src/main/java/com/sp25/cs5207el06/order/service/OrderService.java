package com.sp25.cs5207el06.order.service;

import com.sp25.cs5207el06.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp25.cs5207el06.order.repo.OrderRepo;

import java.util.List;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 10:14 PM
 * @Version 1.0
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    // list all products
    public List<Orders> findOrdersByCustomerId(Long customerId) {
        return orderRepo.findAllByCustomerId(customerId);
    }

    public Orders saveOrder(Orders order) {
        // You can add additional logic here if needed, such as validation or processing
        return orderRepo.save(order);
    }
}
