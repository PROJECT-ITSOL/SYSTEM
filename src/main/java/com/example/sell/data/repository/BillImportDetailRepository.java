package com.example.sell.data.repository;

import com.example.sell.data.model.BillImportDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillImportDetailRepository extends JpaRepository<BillImportDetail,Integer> {


        List<BillImportDetail> getBillImportDetailByIdBillImport(@Param("idBillImport") String idBillImport);


}
