package com.example.sell.data.service;

import com.example.sell.data.model.ProductReturn;
import com.example.sell.data.repository.ProductReturnRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductReturnService {
    private static final Logger logger = LogManager.getLogger(ProductReturnService.class);

    @Autowired
    private ProductReturnRepository productReturnRepository;

    public void addNew(ProductReturn productReturn){
        productReturnRepository.save(productReturn);
    }

    @Transactional
    public void addNewList(List<ProductReturn> productReturns){
        productReturnRepository.saveAll(productReturns);
    }

    public List<ProductReturn> getAllListProductReturn(){
        try {
            return productReturnRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}
