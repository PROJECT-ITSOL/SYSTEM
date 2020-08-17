package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.service.SupplierService;
import com.example.sell.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/supplier")
public class SupplierApiController {
    private static final Logger logger = LogManager.getLogger(SupplierApiController.class);
    @Autowired
    private SupplierService supplierService;

    final String[] images = {"https://xixon-knight.000webhostapp.com/logo-supplier/1.jpg",
            "https://xixon-knight.000webhostapp.com/logo-supplier/2.jpg",
            "https://xixon-knight.000webhostapp.com/logo-supplier/3.jpg",
            "https://xixon-knight.000webhostapp.com/logo-supplier/4.jpg",
            "https://xixon-knight.000webhostapp.com/logo-supplier/5.png"};

    @GetMapping("/fake")
    public BaseApiResult fakeSupplier() {
        BaseApiResult result = new BaseApiResult();
        try {
            List<Supplier> supplierList = new ArrayList<>();
            int totalSupplier = supplierService.getTotalSuppliers();
            for (int i = totalSupplier + 1; i < totalSupplier + 10; i++) {
                Supplier supplier = new Supplier();
                supplier.setIdSupplier(new RandomData().randomText(6));
                supplier.setAddress(new RandomData().randomAddress());
                supplier.setName("Supplier " + i);
                supplier.setStatus(true);
                supplier.setPhoneNumber(new RandomData().randomPhone());
                supplier.setLogo(images[new Random().nextInt(images.length)]);
                supplierList.add(supplier);
            }
            supplierService.addNewSupplier(supplierList);
            result.setSuccess(true);
            result.setMessage("Fake list supplier success!");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }
}
