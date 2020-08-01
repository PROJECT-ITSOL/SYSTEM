package com.example.sell.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_customer")
public class Customer {

    @Id
    @Column(name = "id_customer")
    private String idCustomer;

    @Column(name = "name")
    private String name;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "amount_boom")
    private String amountBoom;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private List<Order> orders =  new ArrayList<>();

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmountBoom() {
        return amountBoom;
    }

    public void setAmountBoom(String amountBoom) {
        this.amountBoom = amountBoom;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
