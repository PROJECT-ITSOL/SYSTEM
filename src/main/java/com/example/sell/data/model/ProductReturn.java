package com.example.sell.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dbo_product_return")
public class ProductReturn {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_product_return")
    private int idProductReturn;

    @Column(name = "id_product", updatable = false, insertable = false)
    private String idProduct;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_product")
    private Product productReturn;

    @Column(name = "id_order", insertable = false, updatable = false)
    private String idOrder;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "id_order")
    private Order orderFail;

    @Column(name = "amount")
    private int amount;

    @Column(name = "status")
    private String status;

}
