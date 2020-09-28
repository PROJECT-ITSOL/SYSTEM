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
    @Query("select count(ord.idOrder) from dbo_order ord")
    int getTotalOrder();
        // tim kiem theo tu ngay date1 toi ngay date 2
    @Query("select oder " +
            "from dbo_order oder " +
            " where oder.createDate>=:date1 or oder.createDate<=:date2")
    List<Order> getListOrderDate(@Param("date") Date date1, Date date2);
    // tim kiem theo ma hoa don
    @Query("select oder from dbo_order oder where oder.idOrder=:idoder")
    Order getOneOrder(@Param("idoder") boolean idoder);
    // tim kiem theo ten khach hang
    @Query("select namekh from dbo_order namekh where namekh.customerOrder.name=:namekh")
    List<Order> getListseachName(@Param("namekh") boolean namekh);
    //tim kiem theo id order

    // tim kiem theo ma khach hang
    @Query("select oder from dbo_order oder " +
            "where (upper(oder.idCustomer) like concat('%',upper(:keyword),'%') )")
    Page<Order> getOrderByIdOrName(Pageable pageable, @Param("keyword") String keyWord);
    // cac ham tim kiem
            // + theo status
    @Query("select oder from dbo_order oder " +
            "where (upper(oder.status) like concat('%',upper(:status),'%') )")
    Page<Order> getListOrderByStatus(Pageable pageable,@Param("status") String status);

    @Query("delete from dbo_order where idOrder=:id ")
    //void deleteInBatch(String id);

    void deleteById(int id);

    @Query("select sum(orderDetail.totalPrice) from dbo_order_detail orderDetail " +
            "where orderDetail.idOrder=:idOrder")
    Double totalMoney(@Param("idOrder") String idOrder);

    // void deleteInBatch(String id);

//    @Query("delete from dbo_order where order=:order ")
//    void deleteOrderById(@Param("id") Order order);

    //void delete(int parseInt);
    // + theo
    //Page<Order> getOrderByName(Pageable pageable, String name);

    // Page<Order> getOrderById


}
