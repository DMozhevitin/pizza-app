package com.example.pizzashop.repository;

import com.example.pizzashop.domain.ProductParams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributesRepository extends JpaRepository<ProductParams, Long> {
}
