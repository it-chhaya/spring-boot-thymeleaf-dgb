package com.chhaya.thymeleaf.service.admin.impl;

import com.chhaya.thymeleaf.model.Category;
import com.chhaya.thymeleaf.repository.admin.impl.CategoryRepositoryImpl;
import com.chhaya.thymeleaf.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepositoryImpl categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepositoryImpl categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        if (categoryRepository.save(category) == 1) {
            return category;
        }
        return null;
    }

}
