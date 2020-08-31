package com.example.sell.data.service;



import com.example.sell.data.model.Supplier;
import com.example.sell.data.repository.SupplierRepository;
import com.example.sell.data.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService{

    @Autowired
    SupplierRepository supplierRepository;



    public List<Supplier> getAllListSuppliers() {
        return supplierRepository.findAll();
    }


    public Supplier getSupplierById(int id) {
        return supplierRepository.findById(id).orElse(null);
    }


    public Supplier findOne(int id) {
        return supplierRepository.findById(id).orElse(null);
    }


    public Boolean deleteSupplierById(int id) {
        try {
            supplierRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Supplier> getListSupplierByStatus(boolean status) {
        return supplierRepository.getListSupplierByStatus(status);
    }


    public Boolean addNewSupplier(Supplier supplier) {
        try {
            supplierRepository.save(supplier);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public List<Supplier> searchSupplier(String name) {
        List<Supplier> listAll = supplierRepository.findAll();
        List<Supplier> listSearch = new ArrayList<>();
        for (Supplier supplier : listAll){
            if(supplier.getName().contains(name)){
                listSearch.add(supplier);
            }
        }
        return listSearch;
    }


}
