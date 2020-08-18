package com.example.sell.data.repository;

import com.example.sell.data.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query("select count(c.idComment) from dbo_comment c")
    int getTotalComment();
}
