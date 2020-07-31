package com.example.pizzashop.dto;

import com.example.pizzashop.domain.Product;
import com.example.pizzashop.domain.ProductParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;

    private String name;

    private String uiName;

    private List<Double> prices;

    private List<Integer> sizes;

    private String description;

    public ProductDTO(Product product) {
        this.id = product.getId();

        this.name = product.getName();
        this.uiName = product.getUiName();
        this.description = product.getDescription();

        this.prices = new ArrayList<>();
        this.sizes = new ArrayList<>();

        IntStream.range(0, product.getAttributes().size()).forEach(i -> {
            ProductParams params = product.getAttributes().get(i);
            this.sizes.add(params.getInch());
            this.prices.add(params.getPrice());
        });
    }
}
