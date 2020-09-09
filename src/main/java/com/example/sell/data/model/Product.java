package com.example.sell.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dbo_product")
public class Product {
    @Id
    @Column(name = "id_product")
    private String idProduct;

    @Column(name = "id_category", insertable = false, updatable = false)
    private String idCategory;

    @ManyToOne(optional = true)
    @JsonIgnore
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "id_supplier", insertable = false, updatable = false)
    private int idSupplier;

    @ManyToOne(optional = true)
    @JsonIgnore
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "favorite")
    private int favorite;

    @Column(name = "amount")
    private int amount;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Comment> comments = new ArrayList<>()  ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productOrderDetail")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToOne(mappedBy = "productReturn")
    private ProductReturn productReturn;

    @OneToOne(mappedBy = "productImport")
    private BillImportDetail billImportDetail;

}
