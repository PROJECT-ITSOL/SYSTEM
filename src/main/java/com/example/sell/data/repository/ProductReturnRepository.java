package com.example.sell.data.repository;

import com.example.sell.data.model.ProductReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductReturnRepository extends JpaRepository<ProductReturn,Integer> {
    //List<ProductReturn> getProductReturn(String id);

    // void saveAll(List<ProductReturn> productReturns);

    //void save(ProductReturn productReturn);

   // List<ProductReturn> findAll();

   List<ProductReturn> getProductReturnByIdOrder(@Param("idProductReturn") int id);

   @Query("select p from dbo_product_return p " +
           "where p.status=:status")
   List<ProductReturn> getProductReturnByIdOrder(@Param("status") boolean status);

   @Query("select p from dbo_product_return p " +
           "where (upper(p.idProduct) like concat('%',upper(:keyword),'%') )")
   Page<ProductReturn> getOrderByIdOrName(Pageable pageable,@Param("keyword") String keyWord);

   //Optional<Object> findById(String id);
}
