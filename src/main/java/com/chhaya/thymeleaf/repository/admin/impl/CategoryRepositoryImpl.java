package com.chhaya.thymeleaf.repository.admin.impl;

import com.chhaya.thymeleaf.model.Category;
import com.chhaya.thymeleaf.repository.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {

        final String SELECT_SQL = "SELECT * FROM categories ORDER BY id DESC";

        return jdbcTemplate.query(SELECT_SQL, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Category(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status"));
            }
        });
    }

    @Override
    public int save(Category category) {

        String insertSql = "INSERT INTO categories (name) VALUES (?)";

        return jdbcTemplate.update(insertSql, category.getName());
    }

    @Override
    public int edit(Category newCategory) {

        final String UPDATE_SQL = "UPDATE categories SET name = ? WHERE id = ?";

        return jdbcTemplate.update(UPDATE_SQL, newCategory.getName(), newCategory.getId());
    }

    @Override
    public Category find(int id) {

        final String SELECT_BY_ID_SQL = "SELECT * FROM categories WHERE id = ?";

        return jdbcTemplate.query(SELECT_BY_ID_SQL,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setInt(1, id);
                    }
                }, new ResultSetExtractor<Category>() {
                    @Override
                    public Category extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            return new Category(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getBoolean("status"));
                        } else {
                            return null;
                        }
                    }
                });
    }

    @Override
    public int delete(int id) {
        final String DELETE_SQL = "DELETE FROM categories WHERE id = ?";
        return jdbcTemplate.update(DELETE_SQL, id);
    }
}
