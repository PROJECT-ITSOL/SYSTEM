package com.example.sell.controller.api;

import com.example.sell.constanst.RandomData;
import com.example.sell.data.model.Category;
import com.example.sell.data.service.CategoryService;
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
}
