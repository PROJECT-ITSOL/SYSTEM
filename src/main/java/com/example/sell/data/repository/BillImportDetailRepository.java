package com.example.sell.data.repository;

import com.example.sell.data.model.BillImportDetail;
import com.example.sell.data.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BillImportDetailRepository extends JpaRepository<BillImportDetail,Integer> {
//    Page<BillImportDetail> getSuppByName(Pageable pageable, @Param("name") String name);
}
