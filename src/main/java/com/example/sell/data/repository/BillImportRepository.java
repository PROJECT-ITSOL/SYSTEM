package com.example.sell.data.repository;

import com.example.sell.data.model.BillImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillImportRepository extends JpaRepository<BillImport,String> {
    //tim kiem hoa don theo ngay
    @Query ("select bill " +
            "from dbo_bill_import bill " +
            "where bill.createDate =:date")
    List<BillImport> getListBillportByDate(@Param("date") boolean date);

    // tim kiem theo id
}
