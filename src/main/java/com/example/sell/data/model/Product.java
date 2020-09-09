package com.example.sell.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_product")
public class Product {
    @Id
    @Column(name = "id_product")
    private String idProduct;

    @Column(name = "id_category", insertable = false, updatable = false)
    private String idCategory;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "id_supplier", insertable = false, updatable = false)
    private String idSupplier;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "productOrderDetail")
    private OrderDetail orderDetail;

    @OneToOne(mappedBy = "productReturn")
    private ProductReturn productReturn;

    @OneToOne(mappedBy = "productImport")
    private BillImportDetail billImportDetail;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public ProductReturn getProductReturn() {
        return productReturn;
    }

    public void setProductReturn(ProductReturn productReturn) {
        this.productReturn = productReturn;
    }

    public void setIdCategory(Category idCategory) {
    }
}
