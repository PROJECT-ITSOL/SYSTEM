package com.example.sell.data.repository;

import com.example.sell.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupplierRepository extends JpaRepository<Supplier,String> {
    @Query("select count (s.idSupplier) from dbo_supplier s")
    int getTotalSuppliers();
}
