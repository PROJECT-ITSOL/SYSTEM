package com.example.sell.data.model;

import javax.persistence.*;

@Entity(name = "dbo_product")
public class Product {
    @Id
    @Column(name = "id_product")
    private String idProduct;

    @Column(name = "id_category",insertable = false, updatable = false)
    private String idCategory;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "id_supplier",insertable = false,updatable = false)
    private String idSupplier;



    @Column(name = "name")
    private String name;

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
}
