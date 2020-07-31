package com.example.pizzashop.dto;

import com.example.pizzashop.domain.Order;
import com.example.pizzashop.domain.ShoppingCart;
import com.example.pizzashop.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    @NotNull(message = "Phone number should not be empty")
    @NotEmpty(message = "Phone number should not be empty")
//    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
//            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
//            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotNull(message = "Address should not be empty")
    @NotEmpty(message = "Address should not be empty")
    private String address;

    @NotNull(message = "Name should not be empty")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    private ShoppingCartDTO cart;

    private String date;

    public OrderDTO(Order order) {
        this.address = order.getAddress();
        this.cart = new ShoppingCartDTO(order.getCart());
        this.date = DateUtils.formatDateISO(order.getCreationTime());
    }
}
