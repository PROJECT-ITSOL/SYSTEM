package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Customer;
import com.example.sell.data.model.Order;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.service.CustomerService;
import com.example.sell.data.service.OrderService;
import com.example.sell.model.dto.CategoryDTO;
import com.example.sell.model.resutlData.BaseApiResult;
import com.example.sell.model.resutlData.DataApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
//    @Autowired
    //private

    //
    //@GetMapping("")
    // public ResponseEntity<?> getL
    @GetMapping("/fake")
    public BaseApiResult faBaseApiResult() {
        BaseApiResult result = new BaseApiResult();
        try {
            List<Order> orders = new ArrayList<>();
            int totalOrder = orderService.getTotalOrder(); // so luong oj
            Random random = new Random();
            RandomData randomData = new RandomData();
            List<Customer> customerList = customerService.getAllListCustomer();

            for (int i = totalOrder + 1; i < totalOrder + 10; i++) {
                Order order = new Order();
//                order.setIdOrder(i); ko can
                order.setIdOrder(new RandomData().randomText(4));
                order.setCustomerOrder(customerList.get(random.nextInt(customerList.size())));
                order.setCreateDate(new Date());
                order.setStatus(new RandomData().radomStatusOrder());
                order.setTotalMoney(random.nextGaussian()); // ti sua lai
                orders.add(order);
            }
            orderService.addNewListOrder(orders);
            result.setSuccess(true);
            result.setMessage("Face list order success");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    // lay  tat ca danh sach
    @GetMapping("")
    public ResponseEntity<?> getListOrder(){
        List<Order> orderList= orderService.getAllOrderList();
        return ResponseEntity.ok(orderList);
    }
    // lay danh sach phan theo trang
    @GetMapping("/list")
    public Page<Order> PageOrder(@RequestParam(value = "page") int page){
        Pageable pageable = PageRequest.of(page,5);
        Page<Order> listPage= orderService.findAll(pageable);
        return listPage;
    }
    // lay theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id){
        Order order= orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    // lay theo status
    @GetMapping("/status")
    public ResponseEntity<?> getSupplierByStatus(@RequestParam(value = "status", required = true) String status) {
        List<Order> listOrder = orderService.getListOrderByStatus(status);
        return ResponseEntity.ok(listOrder);
    }


    // thuc hien xoa doi tuong theo id
    @DeleteMapping("/delete/{id}")
    public BaseApiResult deleteOrder(@PathVariable String id) {
        BaseApiResult result = new BaseApiResult();
        if (orderService.deleteOrder(id)) {
            result.setSuccess(true);
            result.setMessage("Delete success");
        } else {
            result.setSuccess(false);
            result.setMessage("Delete fail");
        }
        return result;
    }

   @PostMapping("/addOrder")
   public BaseApiResult addNewOrder(@RequestBody Order order) {
       BaseApiResult result = new BaseApiResult();
       try {
           orderService.addNewOrder(order);
           result.setSuccess(true);
           result.setMessage("Success add new order !");
       } catch (Exception e) {
           e.printStackTrace();
           result.setSuccess(false);
           result.setMessage("Fail to add new order!");
       }
       return result;

   }
    // update theo id
    @PutMapping("/update/{id}")
    public BaseApiResult updateOrder(@PathVariable String id,@RequestBody Order order){
        BaseApiResult result=new BaseApiResult();
        Order odr= orderService.findOne(id);

        odr.setIdOrder(order.getIdOrder());
        odr.setIdCustomer(order.getIdCustomer());
        odr.setCreateDate(order.getCreateDate());
        odr.setCustomerOrder(order.getCustomerOrder());
        odr.setOrderDetails(order.getOrderDetails());
        odr.setProductReturns(order.getProductReturns());
        odr.setStatus(order.getStatus());
        odr.setTotalMoney(order.getTotalMoney());

        try{
            orderService.addNewOrder(odr);
            result.setMessage("Update succes");
            result.setSuccess(true);
        } catch (Exception e){
            e.printStackTrace();
            result.setMessage("Update fail");
            result.setSuccess(false);
        }
        return result;

    }
    // tim kiem
    @GetMapping("/search")
    public Page<Order> searchOrder(@RequestParam( value = "page") int page,
                                         @RequestParam(name = "name") String name)
    {
        Pageable pageable =  PageRequest.of(page,5);
        Page<Order> listSearch = orderService.searchOrderPage(pageable,name);
        return listSearch ;
    }




//



}