package com.example.pizzashop.controller;

import com.example.pizzashop.domain.User;
import com.example.pizzashop.service.CurrencyService;
import com.example.pizzashop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequiredArgsConstructor
public class CurrencyController {
    private final UserService userService;

    @Autowired
    private HttpSession httpSession;

    @PatchMapping("/api/currency/{userId}")
    public User toggleCurrency(@PathVariable Long userId) {
        return userService.toggleCurrency(userService.loadOrCreate(userId, httpSession));
    }
}
