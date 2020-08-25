package com.example.sell.data.repository;

import com.example.sell.data.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select count(c.idComment) from dbo_comment c")
    int getTotalComment();

    @Query("select cmt from dbo_comment cmt " +
            "join dbo_customer cust on (cmt.idCustomer=cust.idCustomer) " +
            "where cmt.idCustomer=:idCustomer")
    List<Comment> getListCommentByIdCustomer(@Param("idCustomer") String idCustomer);

    @Query("select cmt from dbo_comment cmt " +
            "join dbo_product p on (cmt.idProduct=p.idProduct) " +
            "where cmt.idProduct=:idProduct")
    List<Comment> getListCommentByIdProduct(@Param("idProduct") String idProduct);
}
