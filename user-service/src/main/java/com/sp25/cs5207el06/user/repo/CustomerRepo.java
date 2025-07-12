package com.sp25.cs5207el06.user.repo;

import com.sp25.cs5207el06.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Author monster
 * @Date 2025/6/28 6:40 PM
 * @Version 1.0
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    /**
     * 根据登录名查询用户
     *
     * @param loginName 登录名
     * @return 用户实体
     */
    Customer findByLoginName(String loginName);
}
