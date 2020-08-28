package com.example.sell.data.service;

import com.example.sell.data.model.Category;
import com.example.sell.data.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllListCategories(){
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public void addNewListCategories(List<Category> categories){
        categoryRepository.saveAll(categories);
    }

    public Category findOne(String id){
        return categoryRepository.findById(id).orElse(null);
    }

    public void addNewCategory(Category category){
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public boolean updateCategory(Category category){
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean deleteCategory(String id){
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
    public int getTotalCategories(){
        return categoryRepository.getTotalCategories();
    }

}
