package com.example.pizzashop.controller;


import com.example.pizzashop.domain.Product;
import com.example.pizzashop.domain.ProductParams;
import com.example.pizzashop.domain.ShoppingCart;
import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.OrderDTO;
import com.example.pizzashop.dto.ProductOrderDTO;
import com.example.pizzashop.dto.UserCredentials;
import com.example.pizzashop.repository.ProductRepository;
import com.example.pizzashop.service.OrderService;
import com.example.pizzashop.service.ProductService;
import com.example.pizzashop.service.ShoppingCartService;
import com.example.pizzashop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/api/order/{userId}")
    public void createOrder(@PathVariable Long userId, @Valid @RequestBody OrderDTO orderDTO) {
        User user = userService.loadOrCreate(userId, httpSession);

        ShoppingCart cart = shoppingCartService.findLastByUser(user);

        orderService.createOrder(orderDTO, user, cart);
    }

    @GetMapping("api/orders/{userId}")
    public List<OrderDTO> loadRecentOrders(@PathVariable Long userId) {
        User user = userService.loadOrCreate(userId, httpSession);
        return orderService.loadRecentOrders(user, 10).stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }
}
