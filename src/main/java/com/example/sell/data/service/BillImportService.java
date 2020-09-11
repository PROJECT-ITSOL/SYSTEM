package com.example.sell.data.service;


import com.example.sell.data.model.BillImport;
import com.example.sell.data.repository.BillImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BillImportService {

    @Autowired
    private BillImportRepository billImportRepository;

    public List<BillImport> getAllBillImport(){
        return billImportRepository.findAll();
    }

    public BillImport getBillImportById(String id){
        return billImportRepository.findById(id).orElse(null);
    }


    public Boolean addNewBillImport(BillImport billImport) {
        try {
            billImportRepository.save(billImport);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    //Lấy Bill Import theo page
    public Page<BillImport> getAllPage(Pageable pageable) {
        Page<BillImport> listBillImportPage =  billImportRepository.findAll(pageable);
        return listBillImportPage;
    }

    //Xóa Bill
    public Boolean deleteBillImportById(String id) {
        try {
            billImportRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
