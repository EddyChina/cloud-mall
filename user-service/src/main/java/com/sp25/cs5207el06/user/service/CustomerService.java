package com.sp25.cs5207el06.user.service;

import com.sp25.cs5207el06.entity.Customer;
import com.sp25.cs5207el06.user.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 6:44 PM
 * @Version 1.0
 */
@Service
public class CustomerService {
    // 这里可以注入 CustomerRepo 并实现相关业务逻辑
    @Autowired
    private CustomerRepo customerRepo;

     public Customer findByLoginName(String loginName) {
         return customerRepo.findByLoginName(loginName);
     }

    // 其他业务方法...
}
