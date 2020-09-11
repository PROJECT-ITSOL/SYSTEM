package com.example.sell.model.dto;

import com.example.sell.data.model.Comment;
import com.example.sell.data.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int id;
    private String image;
    private String content;
    private String nameCustomer;
    private String nameProduct;

    public CommentDTO convertComment(Comment comment){
        CommentDTO commentDTO= new CommentDTO();
        commentDTO.setId(comment.getIdComment());
        commentDTO.setImage(comment.getImage());
        commentDTO.setContent(comment.getContent());
        commentDTO.setNameCustomer(comment.getCustomer().getName());
        commentDTO.setNameProduct(comment.getProduct().getName());
        return commentDTO;
    }
}
