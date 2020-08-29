package com.example.sell.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
