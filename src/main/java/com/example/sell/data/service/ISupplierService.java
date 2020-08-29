package com.example.sell.data.service;

import com.example.sell.data.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISupplierService   {
    public List<Supplier> getListAllSupplier();

    public Supplier getSupplierById(int id);

    public Supplier findOne(int id);

    public Boolean deleteSupplierById( int id);

    public List<Supplier> getListSupplierByStatus(boolean status);

    public Boolean addNewSupplier(Supplier supplier);

    public List<Supplier> searchSupplier(String name);

}