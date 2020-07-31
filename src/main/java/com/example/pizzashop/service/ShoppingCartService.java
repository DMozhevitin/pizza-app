package com.example.pizzashop.service;

import com.example.pizzashop.domain.Product;
import com.example.pizzashop.domain.ProductOrder;
import com.example.pizzashop.domain.ShoppingCart;
import com.example.pizzashop.domain.User;
import com.example.pizzashop.dto.ProductOrderDTO;
import com.example.pizzashop.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository repository;

    public ShoppingCart findLastByUser(User user) {
        ShoppingCart cart = repository.findFirstByUserOrderByCreationTimeDesc(user);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
            cart.setProductOrders(new ArrayList<>());
            return repository.save(cart);
        }

        return cart;
    }

    public void updateCart(ShoppingCart cart, ProductOrderDTO orderDTO, Product product) {
        ProductOrder order = new ProductOrder();
        order.setProduct(product);
        order.setQty(orderDTO.getQty());
        order.setSize(orderDTO.getSize());

        boolean found = false;
        for (ProductOrder po : cart.getProductOrders()) {
            if (order.getProduct().getId().equals(po.getProduct().getId())
                    && order.getSize() == po.getSize()) {
                found = true;
                if (orderDTO.isIncrease()) {
                    po.addQty(order.getQty());
                } else {
                    po.setQty(order.getQty());
                }
                break;
            }
        }

        if (!found) {
            cart.addOrder(order);
        }

        repository.save(cart);
    }

    public ShoppingCart deleteProductFromCart(ShoppingCart cart, ProductOrderDTO orderDTO, Product product) {
//        ProductOrder toDelete = null;
//        int idxToDelete = -1;

        cart.setProductOrders(
                cart.getProductOrders().stream()
                        .filter(po -> !(po.getSize() == orderDTO.getSize() && po.getProduct().getId().equals(product.getId())))
                        .collect(Collectors.toList())
        );

//        for (int i = 0; i < cart.getProductOrders().size(); i++) {
//            ProductOrder po = cart.getProductOrders().get(i);
//            if (po.getSize() == orderDTO.getSize() && po.getProduct().getId().equals(product.getId())) {
//
//            }
//        }

        return repository.save(cart);
    }

    public ShoppingCart save(ShoppingCart cart) {
        return repository.save(cart);
    }
}
