package com.example.sell.data.service;

import com.example.sell.data.model.BillImport;
import com.example.sell.data.repository.BillImportRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BilllmportService {

    private static final Logger logger = LogManager.getLogger(BilllmportService.class);

    @Autowired
    private BillImportRepository billImportRepository;

    @Transactional
    public void addNewList(List<BillImport> billImports){
        billImportRepository.saveAll(billImports);
    }

    public void addNew(BillImport billImport){
        billImportRepository.save(billImport);
    }

    public int getTotalBillImport(){
        return billImportRepository.getTotalBillImport();
    }
    public List<BillImport> getList(){
        List<BillImport> list=billImportRepository.findAll();
        return billImportRepository.findAll();
    }
}
