package com.example.sell.data.service;

import com.example.sell.data.model.Comment;
import com.example.sell.data.model.Product;
import com.example.sell.data.repository.CommentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private static final Logger logger = LogManager.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getListComment(){
        try {
            return commentRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean deleteCommentById(int id){
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

}
