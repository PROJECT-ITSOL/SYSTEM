package com.example.sell.data.repository;

import com.example.sell.data.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface SupplierRepository  extends JpaRepository< Supplier, Integer> {


    @Query("select sup from dbo_supplier sup " +
            "where sup.status=:status")
    List<Supplier> getListSupplierByStatus(@Param("status") boolean status) ;



}
