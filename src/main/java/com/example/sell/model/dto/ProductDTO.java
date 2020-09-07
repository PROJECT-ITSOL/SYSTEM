package com.example.sell.model.dto;

import com.example.sell.data.model.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String idProduct;
    private Category idCategory;
    private String idSupplier;
    private String name;
    private double price;
    private String image;
    private String content;
    private int favorite;
    private int amount;
    private Boolean status;
}
