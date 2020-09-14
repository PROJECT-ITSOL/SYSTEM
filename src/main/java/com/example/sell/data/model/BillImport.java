package com.example.sell.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dbo_bill_import")
public class BillImport {
    @Id
    @Column(name = "id_bill_import")
    private String idBillImport;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "total_product")
    private Integer totalProduct;

    @Column(name = "total_money")
    private Double totalMoney;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "billImport")
    private List<BillImportDetail> billImportDetails = new ArrayList<>();

}
