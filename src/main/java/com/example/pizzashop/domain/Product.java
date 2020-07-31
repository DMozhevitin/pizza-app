package com.example.pizzashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends AbstractEntity {

    private String name;

    private String uiName;

    @Lob
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductParams> attributes;
}
