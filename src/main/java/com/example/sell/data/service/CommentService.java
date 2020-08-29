package com.example.sell.data.service;

import com.example.sell.data.model.Comment;
import com.example.sell.data.repository.CommentRepository;
import com.example.sell.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private static final Logger logger = LogManager.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void addNewListComment(List<Comment> commentList) {
        commentRepository.saveAll(commentList);
    }

    public Comment findComment(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public int getTotalComment() {
        return commentRepository.getTotalComment();
    }

    public List<Comment> getListComment() {
        try {
            return commentRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean deleteCommentById(int id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public List<Comment> getListCommentByIdCustomer(String idCustomer) {
        try {
            return commentRepository.getListCommentByIdCustomer(idCustomer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Comment> getListCommentByIdProduct(String idProduct) {
        try {
            return commentRepository.getListCommentByIdProduct(idProduct);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Page<Comment> commentPage(int pageNo, int pageSize) {
        return commentRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

}
