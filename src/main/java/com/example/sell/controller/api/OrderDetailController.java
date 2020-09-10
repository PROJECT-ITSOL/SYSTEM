package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Order;
import com.example.sell.data.model.OrderDetail;
import com.example.sell.data.model.Product;
import com.example.sell.data.service.OrderDetailService;
import com.example.sell.data.service.OrderService;
import com.example.sell.data.service.ProductService;
import com.example.sell.model.resutlData.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/orderdetail")
@CrossOrigin(origins = "*")
public class OrderDetailController {
    // logger dung de text api
    private static final Logger logger = LogManager.getLogger(OrderDetailController.class);

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
//    @Autowired
//    private int gia

    @GetMapping("/fake")
    public BaseApiResult fakeOrderDetail() {
        BaseApiResult result = new BaseApiResult();

        try {
            List<OrderDetail> orderDetails = new ArrayList<>();
            int totalOrderDetail = orderDetailService.getTotailOrderDetail();
            Random random = new Random();
            RandomData randomData = new RandomData();
            // fake list orderlist
            List<Order> ordersList = orderService.getAllOrderList();
            List<Product> productList = productService.findAll();
            for (int i = totalOrderDetail + 1; i < totalOrderDetail + 30; i++) {
                OrderDetail orderDetail = new OrderDetail();
                //orderDetail.setIdOrderDetail();
                // orderDetail.setIdOrder(ordersList.get(random.nextInt(ordersList.size())));
                orderDetail.setOrder(ordersList.get(random.nextInt(ordersList.size())));
                orderDetail.setProductOrderDetail(productList.get(random.nextInt(productList.size())));
                orderDetail.setAmount(100 + i);
                orderDetails.add(orderDetail);

            }
            orderDetailService.addNewListOrderDetail(orderDetails);
            result.setSuccess(true);
            result.setMessage("fake list OrderDetail");

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    // get mapping List lay ra danh sach

    @GetMapping("/list")
    public ResponseEntity<Page<OrderDetail>> getListOrderDetail(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0")
                    int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "7")
                    int pageSize) {
        return new ResponseEntity<Page<OrderDetail>>(orderDetailService.getPageListOrdersDetail(pageNo, pageSize), HttpStatus.OK);
    }


}
