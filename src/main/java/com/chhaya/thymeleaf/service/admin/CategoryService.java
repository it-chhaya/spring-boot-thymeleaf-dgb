package com.chhaya.thymeleaf.service.admin;

import com.chhaya.thymeleaf.model.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    Category edit(Category newCategory);

    Category find(int id);

    void delete(int id);

}
