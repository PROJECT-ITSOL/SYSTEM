package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.service.CategoryService;
import com.example.sell.exception.NotFoundException;
import com.example.sell.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/category")

public class CategoryApiController {

    private static final Logger logger = LogManager.getLogger(CategoryApiController.class);

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/fake")
    public BaseApiResult fakeCategory() {
        BaseApiResult result = new BaseApiResult();
        List<Category> categories = new ArrayList<>();
        try {
            Random random = new Random();
            int totalCategories = categoryService.getTotalCategories();
            for (int i = totalCategories + 1; i < totalCategories + 20; i++) {
                Category category = new Category();
                /*
                 * random code category
                 * */

                category.setIdCategory(new RandomData().randomText(6));
                category.setName("Category " + i);
                category.setStatus(true);
                categories.add(category);
            }
            categoryService.addNewListCategories(categories);
            result.setSuccess(true);
            result.setMessage("Fake list category success!");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getListCategories() {
//        logger.debug("--------------Request ");
        return new ResponseEntity<List<Category>>(categoryService.getAllListCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public BaseApiResult deleteCategory(@PathVariable String id) {
        BaseApiResult result = new BaseApiResult();
        if (categoryService.deleteCategory(id)) {
            result.setSuccess(true);
            result.setMessage("Delete Success !!");
        } else {
            result.setSuccess(false);
            result.setMessage("Delete false !!");
        }
        return result;
    }

    @PostMapping("/addNew")
    public BaseApiResult addNew(@RequestBody Category category) {
        BaseApiResult result = new BaseApiResult();
        try {
            categoryService.addNewCategory(category);
            result.setSuccess(true);
            result.setMessage("Add new category success: " + category.getIdCategory());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Add new category fail!");
            logger.error(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{id}")
    public BaseApiResult updateCategory(@PathVariable String id, @RequestBody Category category) {
        BaseApiResult result = new BaseApiResult();
        Category categoryEntity = categoryService.findOne(id);
        try {
            categoryEntity.setName(category.getName());
            categoryEntity.setStatus(category.getStatus());
            categoryService.addNewCategory(categoryEntity);
            result.setMessage("Update category success!");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @GetMapping("/search")
    public Category getCategory(@RequestParam(value = "id", required = true) String id) {
        if (categoryService.findOne(id)==null){
            throw new NotFoundException("Not Found");
        }
        return categoryService.findOne(id);
    }
}
