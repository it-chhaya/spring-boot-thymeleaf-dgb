package com.chhaya.thymeleaf.repository.admin.impl;

import com.chhaya.thymeleaf.model.Category;
import com.chhaya.thymeleaf.repository.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Category category) {

        String insertSql = "INSERT INTO categories (name) VALUES (?)";

        return jdbcTemplate.update(insertSql, category.getName());
    }

}
