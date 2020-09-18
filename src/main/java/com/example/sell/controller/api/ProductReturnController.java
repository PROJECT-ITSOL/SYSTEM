package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Order;
import com.example.sell.data.model.OrderDetail;
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
import org.springframework.web.bind.annotation.*;

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
        List<ProductReturn> productReturnsList= productReturnService.getAllOrderList();
        return ResponseEntity.ok(productReturnsList);
    }

    @GetMapping("/list")
    public Page<ProductReturn> PageProductReturn(@RequestParam(value = "page") int page){
        Pageable pageable = PageRequest.of(page,5);
        Page<ProductReturn> listPage= productReturnService.findAll(pageable);
        return listPage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id){
        ProductReturn productReturn= productReturnService.getOrderById(id);
        return ResponseEntity.ok(productReturn);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getSupplierByStatus(@RequestParam(value = "status", required = true) boolean status) {
        List<ProductReturn> productReturns = productReturnService.getListOrderByStatus(status);
        return ResponseEntity.ok(productReturns);
    }

    // thuc hien xoa doi tuong theo id
    @DeleteMapping("/delete/{id}")
    public BaseApiResult deleteProductReturn(@PathVariable int id) {
        BaseApiResult result = new BaseApiResult();
        if (productReturnService.deleteOrder(id)) {
            result.setSuccess(true);
            result.setMessage("Delete success");
        } else {
            result.setSuccess(false);
            result.setMessage("Delete fail");
        }
        return result;
    }

    // thuc hien add
    @PostMapping("/addOrder")
    public BaseApiResult addNewProductReturn(@RequestBody ProductReturn productReturn) {
        BaseApiResult result = new BaseApiResult();
        try {
            productReturnService.addNewProductReturn(productReturn);
            result.setSuccess(true);
            result.setMessage("Success add new order !");
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("Fail to add new order!");
        }
        return result;

    }
    // tim kiem
    @GetMapping("/search")
    public Page<ProductReturn> searchProductReturn(@RequestParam( value = "page") int page,
                                   @RequestParam(name = "name") String name)
    {
        Pageable pageable =  PageRequest.of(page,5);
        Page<ProductReturn> listSearch = productReturnService.searchOrderPage(pageable,name);
        return listSearch ;
    }

//    public List<ProductReturn> getProductReturnListById(@RequestParam(value = "idProductReturn",required = true) String id){
//        return  productReturnService.getAllProductReturnListById(id);
//    }

//    @GetMapping("/all")
//    public List<ProductReturnDTO> getProductReturnDTOS(){
//        return
//    }

//    @GetMapping("/list")
//    public List<ProductReturn> getList(){
//        return productReturnService.getAllListProductReturn();
//    }
}
