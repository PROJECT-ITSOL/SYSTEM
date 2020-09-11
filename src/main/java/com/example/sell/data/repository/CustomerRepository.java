package com.example.sell.data.repository;

import com.example.sell.data.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Query("select count (ct.idCustomer) from dbo_customer ct")
    int getTotalCustomers();

    @Query("select ct from dbo_customer ct " +
            "where (upper(ct.idCustomer) like concat('%',upper(:keyword),'%') ) " +
            "or (upper(ct.name) like concat('%',upper(:keyword),'%') )")
    Page<Customer> getCustomersByIdOrName(Pageable pageable, @Param("keyword") String keyWord);
}

