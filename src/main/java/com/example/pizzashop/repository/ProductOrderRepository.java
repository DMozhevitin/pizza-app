package com.example.pizzashop.repository;

import com.example.pizzashop.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
