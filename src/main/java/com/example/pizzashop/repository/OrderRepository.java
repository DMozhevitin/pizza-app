package com.example.pizzashop.repository;

import com.example.pizzashop.domain.Order;
import com.example.pizzashop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserOrderByCreationTimeDesc(User user);

}
