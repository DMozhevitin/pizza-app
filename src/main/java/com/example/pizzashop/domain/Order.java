package com.example.pizzashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orderr")
public class Order extends AbstractEntity {

    @OneToOne
    private ShoppingCart cart;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String name;

    private String address;

    private String phoneNumber;

    @CreationTimestamp
    private Date creationTime;
}
