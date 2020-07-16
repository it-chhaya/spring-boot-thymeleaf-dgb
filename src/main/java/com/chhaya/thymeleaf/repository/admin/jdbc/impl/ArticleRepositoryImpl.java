package com.chhaya.thymeleaf.repository.admin.jdbc.impl;

import com.chhaya.thymeleaf.model.Article;
import com.chhaya.thymeleaf.repository.admin.jdbc.ArticleRepository;
import com.chhaya.thymeleaf.repository.admin.jdbc.mapper.ArticleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Article article) {

        String sql = "INSERT INTO articles" +
                "(article_id," +
                "title," +
                "description," +
                "thumbnail," +
                "author," +
                "published_date," +
                "category_id) " +
                "VALUES (" +
                "?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql, ps -> {
            ps.setString(1, article.getArticleId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getDescription());
            ps.setString(4, article.getThumbnail());
            ps.setString(5, article.getAuthor());
            ps.setDate(6, article.getPublishedDate());
            ps.setInt(7, article.getCategory().getId());
        });
    }

    @Override
    public List<Article> findAll() {

        String sql = "SELECT * FROM articles WHERE status = true ORDER BY id desc";

        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }

    @Override
    public Article find(String articleId) {

        return null;
    }

    @Override
    public int delete(String articleId) {
        return 0;
    }

    @Override
    public int update(Article newArticle) {

        String sql = "UPDATE articles " +
                "SET title = ?," +
                "description = ?," +
                "thumbnail = ?," +
                "category_id = ? " +
                "WHERE article_id = ? AND status = true";

        return jdbcTemplate.update(sql,
                newArticle.getTitle(),
                newArticle.getDescription(),
                newArticle.getThumbnail(),
                newArticle.getCategory().getId(),
                newArticle.getArticleId());

    }

}
