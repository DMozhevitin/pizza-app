package com.example.pizzashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Currency extends AbstractEntity {

   private double coeff; // default: USD

   private String name;

   private String sign;
}
