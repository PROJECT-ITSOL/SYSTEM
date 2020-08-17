package com.example.sell.data.service;

import com.example.sell.data.model.Supplier;
import com.example.sell.data.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllListSuppliers(){
        try {
            return supplierRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    @Transactional
    public void addNewSupplier(List<Supplier> suppliers){
        supplierRepository.saveAll(suppliers);
    }
    public int getTotalSuppliers(){
        return supplierRepository.getTotalSuppliers();
    }
}
