package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Customer;
import com.example.sell.data.service.CustomerService;
import com.example.sell.model.resutlData.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
