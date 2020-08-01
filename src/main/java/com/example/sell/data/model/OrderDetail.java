package com.example.sell.data.model;

import javax.persistence.*;

@Entity(name = "dbo_order_detail")
public class OrderDetail {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_order_detail")
    private int idOrderDetail;

    @Column(name = "id_order",updatable = false,insertable = false)
    private String idOrder;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;

    @Column(name = "id_product",insertable = false,updatable = false)
    private String idProduct;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_product")
    private Product productOrderDetail;

    @Column(name = "amount")
    private int amount;

    public int getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(int idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Product getProductOrderDetail() {
        return productOrderDetail;
    }

    public void setProductOrderDetail(Product productOrderDetail) {
        this.productOrderDetail = productOrderDetail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
