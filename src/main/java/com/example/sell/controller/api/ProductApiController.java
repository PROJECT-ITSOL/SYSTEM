package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Product;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.service.CategoryService;
import com.example.sell.data.service.ProductService;
import com.example.sell.data.service.servicaImpl.SupplierServiceImpl;
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
@RequestMapping("/api/product")
public class ProductApiController {
    private static final Logger logger = LogManager.getLogger(ProductApiController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SupplierServiceImpl supplierService;

    @GetMapping("/fake")
    public BaseApiResult fakeProduct() {
        BaseApiResult result = new BaseApiResult();
        try {
            List<Product> productList = new ArrayList<>();
            Random random = new Random();
            RandomData randomData=new RandomData();
            int totalProduct = productService.getTotalProducts();
            List<Category> categoryList = categoryService.getAllListCategories();
            List<Supplier> supplierList = supplierService.getListAllSupplier();
            for (int i = totalProduct + 1; i < totalProduct + 40; i++) {
                Product product = new Product();
                product.setIdProduct(randomData.randomText(6));
                product.setCategory(categoryList.get(random.nextInt(categoryList.size())));
                product.setSupplier(supplierList.get(random.nextInt(supplierList.size())));
                product.setName("Product " + i);
                product.setImage(randomData.randomImage());
                product.setPrice(randomData.randomNumber(1000,9999));
                product.setContent(randomData.randomText(100));
                product.setFavorite(random.nextInt(25));
                product.setAmount(randomData.randomNumber(25,100));
                product.setStatus(true);
                productList.add(product);
            }
            productService.addNewListProduct(productList);
            result.setSuccess(true);
            result.setMessage("Fake list Product success!");

        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            logger.error(e.getMessage());
        }
        return result;
    }
    @GetMapping("/getList")
    public List<Product> getListProduct(){
        return productService.findAll();
    }
}
