package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Product;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.repository.ProductRepository;
import com.example.sell.data.service.CategoryService;
import com.example.sell.data.service.ProductService;
import com.example.sell.data.service.SupplierService;
import com.example.sell.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private SupplierService supplierService;

    @GetMapping("/fake")
    public BaseApiResult fakeProduct() {
        BaseApiResult result = new BaseApiResult();
        try {
            List<Product> productList = new ArrayList<>();
            Random random = new Random();
            RandomData randomData=new RandomData();
            int totalProduct = productService.getTotalProducts();
            List<Category> categoryList = categoryService.getAllListCategories();
            List<Supplier> supplierList = supplierService.getAllListSuppliers();
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

    @GetMapping("/products")
    public List<Product> findAll() {

        return  productService.findAll();
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findOne(@PathVariable String idProduct){
        return  productService.get(idProduct).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{idProduct}")
    public  ResponseEntity<Void> delete(@PathVariable String idProduct){
        productService.delete(idProduct);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public Product save(@RequestBody Product product){
        product.setStatus(true);
        return productService.save(product);
    }
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Product product){
        productService.update(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
