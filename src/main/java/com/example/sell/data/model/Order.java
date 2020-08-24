package com.example.sell.data.model;

import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "dbo_order")
public class Order {

    @Id
    @Column(name = "id_order")
    private String idOrder;

    @Column(name = "id_customer", updatable = false, insertable = false)
    private String idCustomer;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customerOrder;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private String status;

    @Column(name = "total_money")
    private Double totalMoney;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderFail")
    private List<ProductReturn> productReturns = new ArrayList<>();

    public List<ProductReturn> getProductReturns() {
        return productReturns;
    }

    public void setProductReturns(List<ProductReturn> productReturns) {
        this.productReturns = productReturns;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Customer getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Customer customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
