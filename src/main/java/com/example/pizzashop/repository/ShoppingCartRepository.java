package com.example.pizzashop.repository;

import com.example.pizzashop.domain.ShoppingCart;
import com.example.pizzashop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findFirstByUserOrderByCreationTimeDesc(User user);
}
