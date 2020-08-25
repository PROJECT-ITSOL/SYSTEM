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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> listProduct = new ArrayList<>();


    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
}
