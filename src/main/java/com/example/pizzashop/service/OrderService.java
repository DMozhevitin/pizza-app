package com.example.pizzashop.service;

import com.example.pizzashop.domain.Order;
import com.example.pizzashop.domain.ShoppingCart;
import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.OrderDTO;
import com.example.pizzashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;

    public Order createOrder(OrderDTO orderDTO, User user, ShoppingCart cart) {
        Order order = new Order();
        order.setUser(user);
        order.setName(orderDTO.getName());
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setAddress(orderDTO.getAddress());
        order.setCart(cart);

        ShoppingCart newCart = new ShoppingCart();
        newCart.setUser(user);

        order = orderRepository.save(order);
        shoppingCartService.save(newCart);

        return order;
    }

    public List<Order> loadRecentOrders(User user, int count) {
        List<Order> orders = orderRepository.findByUserOrderByCreationTimeDesc(user);
        return orders.subList(0, Math.min(count, orders.size()));
    }
}
