package com.example.sell.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dbo_supplier")
public class Supplier {
    @Id
    @Column(name = "id_supplier")
    private String idSupplier;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier",fetch = FetchType.LAZY)
    private List<Product> productList =  new ArrayList<>();
}
