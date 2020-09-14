package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Customer;
import com.example.sell.data.model.Order;
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
    private static final Logger logger = LogManager.getLogger(CategoryApiController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

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
            // int totailOrder =  orderService.getAllListOrders();
            List<Customer> customerList = customerService.getAllListCustomer();

            for (int i = totalOrder + 1; i < totalOrder + 30; i++) {
                Order order = new Order();
//                order.setIdOrder(i); ko can
                order.setIdOrder(new RandomData().randomText(6));
                order.setCustomerOrder(customerList.get(random.nextInt(customerList.size())));
                order.setCreateDate(new Date());
                order.setStatus(random.toString());
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

    // get Mapping List
    @GetMapping("/list")
    public ResponseEntity<Page<Order>> getListOrders(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0")
                    int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "7")
                    int pageSize) {
        return new ResponseEntity<Page<Order>>(
                orderService.getPageListOders(pageNo, pageSize), HttpStatus.OK);
    }

    // delete Mapping
    @DeleteMapping("/delete/{id}")
    public BaseApiResult deleteOrder(@PathVariable String id) {
        BaseApiResult result = new BaseApiResult();
        if (orderService.deleteOrder(id)) {
            result.setSuccess(true);
            result.setMessage("delete Success!!!");
        } else {
            result.setSuccess(false);
            result.setMessage("delete false!!");
        }
        return result;
    }
    // PostMapping add new
    // ko can add new

    // PutMapping update
//    @PutMapping("/update/{id}")
//    public BaseApiResult updateOrder(@PathVariable String id, @RequestBody CategoryDTO){
//        BaseApiResult result =new BaseApiResult();
//        Order orderEntity = orderService.findOne(id);
//        try {
//            orderEntity.setIdOrder(id);
//            orderEntity.setName(orderDTO.getName());
//            orderEntity.setStatus(orerDTO.isStatus());
//            orderService.addNewOrder(orderEntity);
//            result.setMessage("Update category success!");
//            result.setSuccess(true);
//        } catch (Exception e) {
//            result.setSuccess(false);
//            result.setMessage(e.getMessage());
//            logger.error(e.getMessage());
//        }
//        return result;
//    }

    @GetMapping("/search")
    public BaseApiResult getOrder(@RequestParam(value = "keyword") String keyWord,
                                  @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize) {
        DataApiResult result = new DataApiResult();

        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        try {
            Page<Order> pageOrder = orderService.getOrderByIdOrName(pageable, keyWord);
            if (pageOrder.isEmpty()) {
                result.setSuccess(false);
                result.setMessage("Not Found");
            } else {
                result.setSuccess(true);
                result.setData(pageOrder);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }


}