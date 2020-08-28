package com.example.sell.data.service.servicaImpl;


import com.example.sell.data.model.Comment;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.repository.SupplierRepository;
import com.example.sell.data.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;


    @Override
    public List<Supplier> getListSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(int id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
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

    @Override
    public Boolean addNewSupplier(Supplier supplier) {
        try {
            supplierRepository.save(supplier);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }



}
