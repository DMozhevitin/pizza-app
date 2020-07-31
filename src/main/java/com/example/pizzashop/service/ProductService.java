package com.example.pizzashop.service;

import com.example.pizzashop.domain.Product;
import com.example.pizzashop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> loadAll() {
        return productRepository.findAll();
    }

    public Product loadById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
