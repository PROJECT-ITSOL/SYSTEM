package com.example.sell.data.repository;

import com.example.sell.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select count(p.idProduct) from dbo_product p")
    int getTotalProducts();

    @Query("select p from dbo_product p " +
            "where (upper(p.idProduct) like concat('%',upper(:keyword),'%') ) " +
            "or (upper(p.name) like concat('%',upper(:keyword),'%') )")
    Page<Product> getProductsByIdOrName(Pageable pageable, @Param("keyword") String keyword);

//    List<Product> sortedByPriceDesc(double price, Pageable pageable);
//    List<Product> sortedByName(String name, Pageable pageable);
//    List<Product> sortedByPriceDescNameAsc(String name, Pageable pageable);

}



