package com.example.pizzashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOrder extends AbstractEntity {

    private int qty;

    private int size;

    @OneToOne
    private Product product;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private ShoppingCart cart;

    public void addQty(int qty) {
        this.qty += qty;
    }
}
