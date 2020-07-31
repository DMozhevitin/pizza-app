package com.example.pizzashop.dto;

import com.example.pizzashop.domain.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductOrderDTO {

    private ProductDTO product;

    private int qty;

    private int size;

    private boolean increase;

    public ProductOrderDTO(ProductOrder order) {
        this.product = new ProductDTO(order.getProduct());
        this.qty = order.getQty();
        this.size = order.getSize();
    }
}
