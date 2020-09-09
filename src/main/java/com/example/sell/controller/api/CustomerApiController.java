package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Customer;
import com.example.sell.data.service.CustomerService;
import com.example.sell.model.api.BaseApiResult;
import com.example.sell.model.api.DataApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {
    private static final Logger logger = LogManager.getLogger(CustomerApiController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/fake")
    public BaseApiResult fakeCustomer() {
        BaseApiResult result = new BaseApiResult();
        try {
            List<Customer> customers = new ArrayList<>();
            int totalCustomer = customerService.getAllListCustomer().size();
            RandomData randomData = new RandomData();
            for (int i = totalCustomer + 1; i < totalCustomer + 20; i++) {
                Customer customer = new Customer();
                customer.setIdCustomer(randomData.randomText(6));
                customer.setName("Customer " + i);
                customer.setPasswordHash(randomData.randomText(3));
                customer.setPhoneNumber(randomData.randomPhone());
                customer.setAddress(randomData.randomAddress());
                customer.setEmail("supplier" + i + "@gmail.com");
                customer.setStatus(true);
                customer.setAmountBoom(0);
                customers.add(customer);
            }
            customerService.addNewListCustomer(customers);
            result.setSuccess(true);
            result.setMessage("Fake list customer success!");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }
    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getListCustomers(@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
                                                            @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize) {
        return new ResponseEntity<Page<Customer>>(customerService.getPageListCustomers(pageNo, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  BaseApiResult deleteCustomer(@PathVariable String id){
        BaseApiResult result = new BaseApiResult();
        if (customerService.deleteCustomer(id)){
            result.setSuccess(true);
            result.setMessage("Delete Success Customer.");
        }else {
            result.setSuccess(false);
            result.setMessage("Delete false.");
        }
        return result;
    }
    @GetMapping("/search")
    public BaseApiResult getCustomer(@RequestParam(value = "keyword") String keyWord,
                                     @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize){
        DataApiResult result = new DataApiResult();
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        try {
            Page<Customer> pageCustomer = customerService.getCustomersByIdOrName(pageable, keyWord);
            if (pageCustomer.isEmpty()){
                result.setSuccess(false);
                result.setMessage("Not Found.");
            }else {
                result.setSuccess(true);
                result.setData(pageCustomer);
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

}
