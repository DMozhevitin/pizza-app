package com.example.pizzashop.controller;

import com.example.pizzashop.dto.ProductDTO;
import com.example.pizzashop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api/products")
    public List<ProductDTO> loadProducts() {
        List<ProductDTO> dtos = productService.loadAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        return dtos;
    }
}
