package com.example.sell.data.repository;

import com.example.sell.data.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    @Query("select count(o.idOrderDetail) from dbo_order_detail o")

    int getTotailOrderDetail();
}
