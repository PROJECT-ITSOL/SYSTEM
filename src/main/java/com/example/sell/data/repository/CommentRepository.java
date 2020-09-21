package com.example.sell.data.repository;

import com.example.sell.data.model.Comment;
import com.example.sell.data.model.Customer;
import com.example.sell.data.model.Product;
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
            "where (upper(cmt.product.name) like concat('%',upper(:keyword),'%') )" +
            "or (upper(cmt.customer.name) like concat('%',upper(:keyword),'%' ) )")
    Iterable<Comment> getListCommentByKeyword(@Param("keyword") String keyword);
}
