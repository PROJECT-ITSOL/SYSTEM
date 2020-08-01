package com.example.sell.data.model;

import javax.persistence.*;

@Entity(name = "dbo_bill_import_detail")
public class BillImportDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_bill_import_detail")
    private int id;

    @Column(name = "id_bill_import",insertable = false,updatable = false)
    private String idBillImport;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bill_import")
    private BillImport billImport;

    @Column(name = "id_product")
    private String idProduct;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_product")
    private Product productImport;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdBillImport() {
        return idBillImport;
    }

    public void setIdBillImport(String idBillImport) {
        this.idBillImport = idBillImport;
    }

    public BillImport getBillImport() {
        return billImport;
    }

    public void setBillImport(BillImport billImport) {
        this.billImport = billImport;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Product getProductImport() {
        return productImport;
    }

    public void setProductImport(Product productImport) {
        this.productImport = productImport;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
