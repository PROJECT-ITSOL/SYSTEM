package com.example.sell.model.dto;

import com.example.sell.data.model.BillImport;
import com.example.sell.data.service.BillImportDetailService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillImportDTO {


     BillImportDetailService billImportDetailService = new BillImportDetailService();

    private String idBillImport;
    private Date createDate;
    private int totalProduct;
    private Double totalMoney;



    public BillImportDTO convertBill(BillImport billImport) {
        BillImportDTO billImportDTO = new BillImportDTO();
        billImportDTO.setIdBillImport(billImport.getIdBillImport());
        billImportDTO.setCreateDate(billImport.getCreateDate());

        return billImportDTO;
    }
}
