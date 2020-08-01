package com.example.sell.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_category")
public class Category {
    @Id
    @Column(name = "id_category")
    private String idCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Product> listProduct = new ArrayList<>();



}
