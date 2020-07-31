package com.example.pizzashop.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "user", indexes = {
        @Index(columnList = "email, passwordSha", unique = true),
        @Index(columnList = "token", unique = true)
})
public class User extends AbstractEntity {

    private String email;

    private String passwordSha;

    private String phoneNumber;

    private String name;

    @ManyToOne
    private Currency currency;

    private String token;

    private boolean guest;
}
