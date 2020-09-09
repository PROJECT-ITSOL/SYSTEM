package com.example.sell.data.repository;

import com.example.sell.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select count(p.idProduct) from dbo_product p")
    int getTotalProducts();
}
