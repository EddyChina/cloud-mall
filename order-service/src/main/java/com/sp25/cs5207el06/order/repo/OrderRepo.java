package com.sp25.cs5207el06.order.repo;

import com.sp25.cs5207el06.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 10:13 PM
 * @Version 1.0
 */
@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {

    List<Orders> findAllByCustomerId(Long customerId);
}
