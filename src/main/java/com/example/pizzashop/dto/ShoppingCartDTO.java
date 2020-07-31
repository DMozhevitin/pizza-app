package com.example.pizzashop.dto;

import com.example.pizzashop.domain.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShoppingCartDTO {

    private List<ProductOrderDTO> cartItems;

    private Long userId;

    public ShoppingCartDTO(ShoppingCart cart) {
        this.cartItems = cart.getProductOrders().stream()
                .map(ProductOrderDTO::new)
                .collect(Collectors.toList());

        this.userId = cart.getUser().getId();
    }
}
