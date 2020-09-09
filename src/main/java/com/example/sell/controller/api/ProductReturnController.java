package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Order;
import com.example.sell.data.model.OrderDetail;
import com.example.sell.data.model.ProductReturn;
import com.example.sell.data.service.OrderDetailService;
import com.example.sell.data.service.OrderService;
import com.example.sell.data.service.ProductReturnService;
import com.example.sell.data.service.ProductService;
import com.example.sell.model.resutlData.BaseApiResult;
import com.example.sell.model.resutlData.DataApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/productReturn")
public class ProductReturnController {

    private static final Logger logger = LogManager.getLogger(ProductReturnController.class);

    @Autowired
    private ProductReturnService productReturnService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/fake")
    public BaseApiResult fakeData() {
        BaseApiResult result = new BaseApiResult();
        List<Order> orderList = orderService.getAllOrderList();
        List<ProductReturn> productReturns=new ArrayList<>();
        int totalOrder=orderService.getTotalOrder();
        List<OrderDetail> orderDetails;
        Random random = new Random();
        try {
            for (int i = totalOrder + 1; i <totalOrder + orderList.size(); i++) {
                ProductReturn productReturn=new ProductReturn();
                Order order=orderList.get(random.nextInt(orderList.size()));
                orderDetails=orderDetailService.getOrderDetailsByIdOrder(order.getIdOrder());
                if (!orderDetails.isEmpty()){
                    OrderDetail orderDetail=orderDetails.get(random.nextInt(orderDetails.size()));
                    productReturn.setOrderFail(order);
                    productReturn.setProductReturn(productService.getProductById(orderDetail.getIdProduct()));
                    productReturn.setAmount(random.nextInt(20));
                    productReturn.setStatus(new RandomData().randomStatusProductReturn());
    //                productReturn
                    productReturns.add(productReturn);
                }
            }
            productReturnService.addNewList(productReturns);
            result.setSuccess(true);
            result.setMessage("Fake List Product Return Success!");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

//    @GetMapping("/list")
//    public List<ProductReturn> getList(){
//        return productReturnService.getAllListProductReturn();
//    }
}
