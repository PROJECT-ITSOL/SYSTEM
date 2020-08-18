package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Comment;
import com.example.sell.data.model.Customer;
import com.example.sell.data.model.Product;
import com.example.sell.data.service.CommentService;
import com.example.sell.data.service.CustomerService;
import com.example.sell.data.service.ProductService;
import com.example.sell.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/comment")
public class CommentApiController {
    private static final Logger logger = LogManager.getLogger(CommentApiController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/fake")
    public BaseApiResult fakeComment() {
        BaseApiResult result = new BaseApiResult();

        try {
            List<Comment> commentList = new ArrayList<>();
            int totalComment = commentService.getTotalComment();
            Random random = new Random();
            RandomData randomData=new RandomData();
            List<Customer> customerList = customerService.getAllListCustomer();
            List<Product> productList=productService.findAll();
            for (int i = totalComment + 1; i < totalComment + 30; i++) {
                Comment comment=new Comment();
//                comment.setIdComment(i);
                comment.setCustomer(customerList.get(random.nextInt(customerList.size())));
                comment.setProduct(productList.get(random.nextInt(productList.size())));
                comment.setContent(randomData.randomText(100));
                comment.setImage("https://xixon-knight.000webhostapp.com/product-gym/comment.jpg");
                comment.setCreateDate(new Date());
                commentList.add(comment);
            }
                commentService.addNewListComment(commentList);
            result.setSuccess(true);
            result.setMessage("Fake list content success!");

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

}
