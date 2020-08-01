package com.example.sell.data.model;

import javax.persistence.*;

@Entity(name = "dbo_product_return")
public class ProductReturn {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_product_return")
    private int idProductReturn;

    @Column(name = "id_product",updatable = false,insertable = false)
    private String idProduct;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_product")
    private Product productReturn;

    @Column(name = "id_order",insertable = false,updatable = false)
    private String idOrder;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order orderFail;

    @Column(name = "amount")
    private int amount;

    @Column(name = "status")
    private String status;

    public int getIdProductReturn() {
        return idProductReturn;
    }

    public void setIdProductReturn(int idProductReturn) {
        this.idProductReturn = idProductReturn;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Product getProductReturn() {
        return productReturn;
    }

    public void setProductReturn(Product productReturn) {
        this.productReturn = productReturn;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public Order getOrderFail() {
        return orderFail;
    }

    public void setOrderFail(Order orderFail) {
        this.orderFail = orderFail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
