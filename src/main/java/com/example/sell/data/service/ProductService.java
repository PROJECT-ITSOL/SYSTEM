package com.example.sell.data.service;

import com.example.sell.data.model.Product;
import com.example.sell.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public int getTotalProducts(){
        return productRepository.getTotalProducts();
    }
    @Transactional
    public void addNewListProduct(List<Product> products){
        productRepository.saveAll(products);
    }
}
