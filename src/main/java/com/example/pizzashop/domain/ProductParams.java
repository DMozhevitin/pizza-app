package com.example.pizzashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductParams extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int inch;

    private double price;
}
