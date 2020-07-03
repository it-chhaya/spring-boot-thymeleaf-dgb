package com.chhaya.thymeleaf.repository.admin;

import com.chhaya.thymeleaf.model.Category;

import java.util.List;

public interface CategoryRepository {

    int save(Category category);

    List<Category> findAll();

    int edit(Category newCategory);

    Category find(int id);

    int delete(int id);

}
