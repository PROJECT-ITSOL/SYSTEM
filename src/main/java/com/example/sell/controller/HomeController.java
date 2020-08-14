package com.example.sell.controller;

import com.example.sell.data.model.Category;
import com.example.sell.data.model.Product;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.service.ProductService;
import com.example.sell.data.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class HomeController {
    @Autowired
//    private ProductService productService;
    private SupplierService supplierService;
    @GetMapping("/supplier")
//    public ResponseEntity home(){
//        return new ResponseEntity("success", HttpStatus.OK);
//    }
//    public List<Product> getListProduct(){
//        return productService.findAll();
//    }
    public List<Supplier> getListSupplier(){
        return supplierService.suppliers();
    }

    @GetMapping("/home")
    public ResponseEntity home(){
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
