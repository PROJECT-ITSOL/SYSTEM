package com.example.sell.model.dto;

import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillImportDetailDTO {
    private int id;
    private String idBillImport;
    private String idProduct;
    private Product product;
    private int amount;
    private double price;




    public BillImportDetailDTO convertBillDetail(BillImportDetail billImportDetail){
        BillImportDetailDTO billImportDetailDTO = new BillImportDetailDTO();
        billImportDetailDTO.setId(billImportDetail.getId());
        billImportDetailDTO.setAmount(billImportDetail.getAmount());
        billImportDetailDTO.setIdBillImport(billImportDetail.getIdBillImport());
        billImportDetailDTO.setPrice(billImportDetail.getProductImport().getPrice()*billImportDetail.getAmount());
        billImportDetailDTO.setProduct(billImportDetail.getProductImport());
        billImportDetailDTO.setIdProduct(billImportDetail.getIdProduct());

        return billImportDetailDTO;
    }
}
