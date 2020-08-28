package com.example.sell.data.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Component
@Entity(name = "dbo_supplier")
public class Supplier {
    @Id
    @Column(name = "id_supplier")
    private int idSupplier;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "logo")
    private String logo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> productList =  new ArrayList<>();

    public Supplier(){


    }
    public Supplier(int idSupplier,String name,String phoneNumber,String address,String logo){
        this.idSupplier = idSupplier;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.logo = logo;

    }



    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
