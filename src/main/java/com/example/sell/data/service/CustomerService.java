package com.example.sell.data.service;

import com.example.sell.data.model.Customer;
import com.example.sell.data.repository.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static final Logger logger = LogManager.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void addNewListCustomer(List<Customer> customers){
        customerRepository.saveAll(customers);
    }


    public List<Customer> getAllListCustomer(){
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean updateCustomer(Customer customer){
        try {
            customerRepository.save(customer);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
//    public boolean deleteCustomer(String id){
//        try {
//            customerRepository.deleteById(id);
//            return true;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return false;
//        }
//    }
}
