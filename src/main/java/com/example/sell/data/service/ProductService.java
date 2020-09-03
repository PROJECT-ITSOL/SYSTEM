package com.example.sell.data.service;

import com.example.sell.data.model.Category;
import com.example.sell.data.model.Product;
import com.example.sell.data.repository.CategoryRepository;
import com.example.sell.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> get(String idProduct){
        return productRepository.findById(idProduct);
    }

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

    public  void  delete(String idProduct) {productRepository.deleteById(idProduct);}

    public Product save(Product product) {return  productRepository.save(product);}

    public Product update(Product product) {return productRepository.save(product);}
}
