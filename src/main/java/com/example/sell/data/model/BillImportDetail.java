package com.example.sell.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "id_product",insertable = false,updatable = false)
    private String idProduct;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_product")
    private Product productImport;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private Double price;

}
