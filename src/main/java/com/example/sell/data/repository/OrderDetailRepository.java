package com.example.sell.data.repository;

import com.example.sell.data.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
}
