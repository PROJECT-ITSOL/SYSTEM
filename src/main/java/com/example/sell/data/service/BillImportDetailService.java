package com.example.sell.data.service;


import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.repository.BillImportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillImportDetailService {

    @Autowired
    private BillImportDetailRepository billImportDetailRepository;

    public List<BillImportDetail> getBillByIdBillImport(String id){
        return billImportDetailRepository.getBillImportDetailByIdBillImport(id);
    }


    public List<BillImportDetail> getBill(){
        return billImportDetailRepository.findAll();
    }


}
