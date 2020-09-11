package com.example.sell.data.repository;

import com.example.sell.data.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    @Query("select count(o.idOrderDetail) from dbo_order_detail o")
    int getTotailOrderDetail();

    @Transactional(readOnly = true)
    @Query("select o from dbo_order_detail o " +
            "where o.idOrder=:idOrder")
    List<OrderDetail> getOrderDetailByIdOrder(@Param("idOrder") String idOrder);
}
