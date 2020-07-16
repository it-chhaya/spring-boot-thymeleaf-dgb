package com.chhaya.thymeleaf.service.admin.impl;

import com.chhaya.thymeleaf.model.Category;
import com.chhaya.thymeleaf.repository.admin.jdbc.impl.CategoryRepositoryImpl;
import com.chhaya.thymeleaf.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepositoryImpl categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepositoryImpl categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        if (categoryRepository.save(category) == 1) {
            return category;
        }
        return null;
    }

    @Override
    public Category edit(Category newCategory) {
        if (categoryRepository.find(newCategory.getId()) != null)
            if (categoryRepository.edit(newCategory) == 1)
                return newCategory;
        return null;
    }

    @Override
    public Category find(int id) {
        return categoryRepository.find(id);
    }

    @Override
    public void delete(int id) {
        if (categoryRepository.find(id) != null)
            categoryRepository.delete(id);
    }

}
