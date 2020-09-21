package com.example.sell.data.repository;

import com.example.sell.data.model.BillImport;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// tim kiem theo ngay
// tim kiem theo ma hoa don
// tim kiem theo ten khach hang
public interface OrderRepository extends JpaRepository<Order,String> {
    // fake data
    @Query("select count(o.idOrder) from dbo_order o")
    int getTotalOrder();
        // tim kiem theo ngay date
    @Query("select oder " +
            "from dbo_order oder " +
            "where oder.createDate=:date")
    List<Order> getListOrderDate(@Param("date") Date date);
    // tim kiem theo ma hoa don
    //@Query("select oder from dbo_order oder where oder.idOrder=:idoder")
   // List<Order> getOneOrder(@Param("idoder") boolean idoder);
    // tim kiem theo ten khach hang
    //@Query("select namekh from dbo_order namekh where namekh.customerOrder.name=:namekh")
    //List<Order> getListseachName(@Param("namekh") boolean namekh);

    @Query("select oder from dbo_order oder " +
            "where (upper(oder.idOrder) like concat('%',upper(:keyword),'%') )")
    Page<Order> getOrderByIdOrName(Pageable pageable, @Param("keyword") String keyWord);


    // cac ham tim kiem
            // + theo status
    @Query("select oder from dbo_order oder " +
            "where oder.status=:status")
    List<Order> getListOrderByStatus(@Param("status") boolean status);

    @Query("delete from dbo_order where idOrder=:id ")
    //void deleteInBatch(String id);

    void deleteById(int id);

    // void deleteInBatch(String id);

//    @Query("delete from dbo_order where order=:order ")
//    void deleteOrderById(@Param("id") Order order);

    //void delete(int parseInt);
    // + theo
    //Page<Order> getOrderByName(Pageable pageable, String name);

    // Page<Order> getOrderById


}
