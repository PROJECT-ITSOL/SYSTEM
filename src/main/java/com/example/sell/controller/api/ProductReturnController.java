package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Order;
import com.example.sell.data.model.OrderDetail;
import com.example.sell.data.model.Product;
import com.example.sell.data.model.ProductReturn;
import com.example.sell.data.service.OrderDetailService;
import com.example.sell.data.service.OrderService;
import com.example.sell.data.service.ProductReturnService;
import com.example.sell.data.service.ProductService;
import com.example.sell.model.dto.ProductReturnDTO;
import com.example.sell.model.resutlData.BaseApiResult;
import com.example.sell.model.resutlData.DataApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/productReturn")
@CrossOrigin(origins = "*")
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
                    productReturn.setProductReturn(productService.findOne(orderDetail.getIdProduct()));
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

    @GetMapping("")
    public ResponseEntity<?> getListProductDetail(){
        List<ProductReturn> productReturnsList= productReturnService.getAllProductReturnList();
        return ResponseEntity.ok(productReturnsList);
    }
    // lay danh sach phan theo trang
    @GetMapping("/list")
    public Page<ProductReturn> PageOrder(@RequestParam(value = "page") int page){
        Pageable pageable = PageRequest.of(page,5);
        Page<ProductReturn> listPage= productReturnService.findAll(pageable);
        return listPage;
    }


    

    @GetMapping("/status")
    public ResponseEntity<?> getSupplierByStatus(@RequestParam(value = "status", required = true) String status) {
        List<ProductReturn> productReturns = productReturnService.getListOrderByStatus(status);
        return ResponseEntity.ok(productReturns);
    }

    // thuc hien xoa doi tuong theo id
    @DeleteMapping("/delete/{id}")
    public BaseApiResult deleteProductReturn(@PathVariable int id) {
        BaseApiResult result = new BaseApiResult();
        if (productReturnService.deleteProductReturn(id)) {
            result.setSuccess(true);
            result.setMessage("Delete success");
        } else {
            result.setSuccess(false);
            result.setMessage("Delete fail");
        }
        return result;
    }

    // thuc hien add
    @PostMapping("/addProductReturn")
    public BaseApiResult addNewProductReturn(@RequestBody ProductReturn productReturn) {
        BaseApiResult result = new BaseApiResult();

        try {
            Product product=productService.findOne(productReturn.getIdProduct());
            Order order=orderService.getOrderById(productReturn.getIdOrder());
            ProductReturn pr=productReturnService.getOne(productReturn.getIdProductReturn());
            if (pr==null){
                pr=new ProductReturn();
                pr.setOrderFail(order);
                pr.setProductReturn(product);
                pr.setAmount(productReturn.getAmount());
                pr.setStatus(productReturn.getStatus());
                productReturnService.addNewProductReturn(pr);
                result.setSuccess(true);
                result.setMessage("Success add new order !");
            }
            else {
                result.setSuccess(false);
                result.setMessage("Success add fail order !");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("Fail to add new order!");
        }
        return result;

    }
    //@PutMapping("update/{}")
    // tim kiem
    @GetMapping("/search")
    public Page<ProductReturn> searchProductReturn(@RequestParam( value = "page") int page,
                                   @RequestParam(name = "name") String name)
    {
        Pageable pageable =  PageRequest.of(page,5);
        Page<ProductReturn> listSearch = productReturnService.searchProductReturnPage(pageable,name);
        return listSearch ;
    }
    // tim kiem theo status
//    @GetMapping("/seachStatus")
//        public Page<ProductReturn> searchProductReturnStatus(@RequestParam( value = "page") int page,
//                                                             @RequestParam(name = "status") boolean status){
//
//            Pageable pageable =  PageRequest.of(page,5);
//            Page<ProductReturn> listSearch = productReturnService.searchProductReturnStatusPage(pageable,status);
//            return listSearch ;
//        }

//
}
