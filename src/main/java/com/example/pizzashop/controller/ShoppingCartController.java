package com.example.pizzashop.controller;

import com.example.pizzashop.domain.Product;
import com.example.pizzashop.domain.ShoppingCart;
import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.ProductOrderDTO;
import com.example.pizzashop.dto.ShoppingCartDTO;
import com.example.pizzashop.service.ProductService;
import com.example.pizzashop.service.ShoppingCartService;
import com.example.pizzashop.service.TokenService;
import com.example.pizzashop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final TokenService tokenService;

    @Autowired
    private HttpSession httpSession;

    @PutMapping("/api/cart/{id}")
    public void updateShoppingCart(@PathVariable Long id,
                                             @RequestBody ProductOrderDTO orderDTO,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        User user = userService.loadOrCreate(id, httpSession);

        ShoppingCart cart = shoppingCartService.findLastByUser(user);
        Product product = productService.loadById(orderDTO.getProduct().getId());

        shoppingCartService.updateCart(cart, orderDTO, product);
    }

    @GetMapping("api/cart/{id}")
    public ShoppingCartDTO loadShoppingCartByUserId(@PathVariable Long id) {
        User user = userService.loadOrCreate(id, httpSession);
        ShoppingCart cart = shoppingCartService.findLastByUser(user);

        return new ShoppingCartDTO(cart);
    }

    @PostMapping("api/cart/{id}")
    public ShoppingCartDTO deleteItemInShoppingCartByUserId(@PathVariable Long id,
                                                            @RequestBody ProductOrderDTO orderDTO) {
        User user = userService.loadOrCreate(id, httpSession);
        ShoppingCart cart = shoppingCartService.findLastByUser(user);
        Product product = productService.loadById(orderDTO.getProduct().getId());

        return new ShoppingCartDTO(shoppingCartService.deleteProductFromCart(cart, orderDTO, product));
    }
}
