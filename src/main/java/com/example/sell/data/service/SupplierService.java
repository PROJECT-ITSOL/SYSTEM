package com.example.sell.data.service;

import com.example.sell.data.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplierService   {
    public List<Supplier> getListSupplier();

    public Supplier getSupplierById(int id);

    public Boolean deleteSupplierById( int id);

    public List<Supplier> getListSupplierByStatus(boolean status);

}