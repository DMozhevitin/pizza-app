package com.example.pizzashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCart extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Date creationTime;

    public void addOrder(ProductOrder order) {
        if (getProductOrders() == null) {
            setProductOrders(new ArrayList<>());
        }
        getProductOrders().add(order);
    }
}
