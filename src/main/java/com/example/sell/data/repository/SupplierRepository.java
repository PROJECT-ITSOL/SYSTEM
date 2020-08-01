package com.example.sell.data.repository;

import com.example.sell.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,String> {
}
