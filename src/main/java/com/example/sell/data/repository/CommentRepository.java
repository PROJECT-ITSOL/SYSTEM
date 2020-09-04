package com.example.sell.data.repository;

import com.example.sell.data.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select count(c.idComment) from dbo_comment c")
    int getTotalComment();

    @Query("select cmt from dbo_comment cmt " +
            "where cmt.idCustomer=:idCustomer")
    List<Comment> getListCommentByIdCustomer(@Param("idCustomer") String idCustomer);

    @Query("select cmt from dbo_comment cmt " +
            "where cmt.idProduct=:idProduct")
    List<Comment> getListCommentByIdProduct(@Param("idProduct") String idProduct);

    @Query("select cmt from dbo_comment cmt " +
            "where (upper(cmt.idProduct) like concat('%',upper(:id),'%') )" +
            "or (upper(cmt.idCustomer) like concat('%',upper(:id),'%' ) )")
    Page<Comment> getListCommentById(Pageable pageable, @Param("id") String id);
}
