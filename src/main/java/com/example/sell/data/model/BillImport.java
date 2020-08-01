package com.example.sell.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "dbo_bill_import")
public class BillImport {
    @Id
    @Column(name = "id_bill_import")
    private String idBillImport;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "total_money")
    private Double totalMoney;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "billImport")
    private List<BillImportDetail> billImportDetails = new ArrayList<>();

    public String getIdBillImport() {
        return idBillImport;
    }

    public void setIdBillImport(String idBillImport) {
        this.idBillImport = idBillImport;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<BillImportDetail> getBillImportDetails() {
        return billImportDetails;
    }

    public void setBillImportDetails(List<BillImportDetail> billImportDetails) {
        this.billImportDetails = billImportDetails;
    }
}
