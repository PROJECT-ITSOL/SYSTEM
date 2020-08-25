package com.example.sell.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "dbo_comment")
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_comment")
    private int idComment;

    @Column(name = "id_customer", updatable = false, insertable = false)
    private String idCustomer;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "id_Product", insertable = false, updatable = false)
    private String idProduct;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "create_date")
    private Date createDate;

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
