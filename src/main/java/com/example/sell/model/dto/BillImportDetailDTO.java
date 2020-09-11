package com.example.sell.model.dto;

import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.model.Product;
import com.example.sell.data.repository.ProductRepository;
import com.example.sell.data.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


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
        billImportDetailDTO.setPrice(billImportDetail.getPrice());
        billImportDetailDTO.setProduct(billImportDetail.getProductImport());
        return billImportDetailDTO;
    }
}
