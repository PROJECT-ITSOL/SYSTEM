package com.example.sell.model.dto;

import javax.persistence.Column;

public class SupplierDTO {

    private String name;

    private String phoneNumber;

    private String address;

    private Boolean status;

    private String logo;

    public SupplierDTO() {
    }

    public SupplierDTO(String name, String phoneNumber, String address, Boolean status, String logo) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.logo = logo;
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
}
