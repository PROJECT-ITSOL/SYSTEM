package com.example.sell.data.service;

import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.repository.BillImportDetailRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BilllmportDetailService {
    private static final Logger logger= LogManager.getLogger(BilllmportDetailService.class);

    @Autowired
    private BillImportDetailRepository billImportDetailRepository;

    public void addNew(BillImportDetail billImportDetail){
        billImportDetailRepository.save(billImportDetail);
    }

    @Transactional
    public void addNewList(List<BillImportDetail> list){
        billImportDetailRepository.saveAll(list);
    }

    public List<BillImportDetail> getList(){
        return billImportDetailRepository.findAll();
    }
}
