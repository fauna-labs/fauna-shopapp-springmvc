package com.parkhomenko.fauna.service;

import com.parkhomenko.fauna.model.Category;
import com.parkhomenko.fauna.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() throws ExecutionException, InterruptedException {
        return categoryRepository.allCategories();
    }

    public List<String> getCategoryNames() throws ExecutionException, InterruptedException {
        return categoryRepository.allCategoryNames();
    }
}
