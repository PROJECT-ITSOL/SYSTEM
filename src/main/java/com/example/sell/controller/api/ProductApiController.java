package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.model.Product;
import com.example.sell.data.model.Supplier;
import com.example.sell.data.service.CategoryService;
import com.example.sell.data.service.ProductService;
import com.example.sell.data.service.SupplierService;
import com.example.sell.model.api.BaseApiResult;
import com.example.sell.model.api.DataApiResult;
import com.example.sell.model.dto.ProductDTO;
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
            productService.addNewListProducts(productList);
            result.setSuccess(true);
            result.setMessage("Fake list Product success!");

        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            logger.error(e.getMessage());
        }
        return result;
    }

// API Lấy ra danh sách sản phẩm.
    @GetMapping("/list")
    public ResponseEntity<Page<Product>> getListProducts(@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize){
        return new ResponseEntity<Page<Product>>(productService.getPageListProducts(pageNo, pageSize), HttpStatus.OK);
    }

// API xóa sản phẩm.
    @DeleteMapping("/delete")
    public  BaseApiResult deleteProduct(@RequestParam(value = "id") String idProduct){
        BaseApiResult result = new BaseApiResult();
        if (productService.deleteProduct(idProduct)){
           result.setSuccess(true);
           result.setMessage("Delete Success.");
        }else {
            result.setMessage("Delete false.");
            result.setSuccess(false);
        }
        return result;
    }

// API thêm sản phẩm.
    @PostMapping("/addNew")
   public BaseApiResult addNew(@RequestBody ProductDTO productDTO){
        BaseApiResult result = new BaseApiResult();

        Product product = productService.findOne(productDTO.getIdProduct());
        if (product == null){
            try {
                product = new Product();
                product.setIdProduct(productDTO.getIdProduct());
                product.setIdCategory(productDTO.getIdCategory());
                product.setIdSupplier(productDTO.getIdSupplier());
                product.setName(productDTO.getName());
                product.setPrice(productDTO.getPrice());
                product.setContent(productDTO.getContent());
                product.setFavorite(productDTO.getFavorite());
                product.setImage(productDTO.getImage());
                product.setAmount(productDTO.getAmount());

                product.setStatus(true);
                productService.addNewProduct(product);
                result.setSuccess(true);
                result.setMessage("Add new product success: " +product.getIdProduct());
            }catch (Exception e){
                result.setSuccess(false);
                result.setMessage("Add new product fail.");
                logger.error(e.getMessage());
            }
        }else {
            result.setSuccess(false);
            result.setMessage("ID Already exitst.");
        }
        return result;
    }

// API Cập nhập(Sửa) sản phẩm.
    @PutMapping("/update")
    public BaseApiResult updateProduct(@RequestParam(value = "id") String id, @RequestBody ProductDTO productDTO){
      BaseApiResult result = new BaseApiResult();
      Product productEntity = productService.findOne(id);
      try {
          productEntity.setIdProduct(id);
          productEntity.setIdCategory(id);
          productEntity.setIdSupplier(id);
          productEntity.setName(productDTO.getName());
          productEntity.setPrice(productDTO.getPrice());
          productEntity.setStatus(productDTO.getStatus());
          productEntity.setImage(productDTO.getImage());
          productEntity.setContent(productDTO.getContent());
          productEntity.setFavorite(productDTO.getFavorite());
          productEntity.setAmount(productDTO.getAmount());
          productService.addNewProduct(productEntity);
          result.setMessage("Update product success.");
          result.setSuccess(true);
      }catch (Exception e){
          result.setSuccess(false);
          result.setMessage(e.getMessage());
      }
      return result;
    }

// API tìm kiếm sản phẩm.
    @GetMapping("/search")
    public BaseApiResult getProduct(@RequestParam(value = "keyword") String keyWord,
                                    @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize) {
        DataApiResult result = new DataApiResult();
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        try {
            Page<Product> pageProduct = productService.getProductsByIdOrName(pageable, keyWord);
            if (pageProduct.isEmpty()){
                result.setSuccess(false);
                result.setMessage("Not Found");
            }else {
                result.setSuccess(true);
                result.setData(pageProduct);
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            logger.error(e.getMessage());
        }
        return result;
    }

}
