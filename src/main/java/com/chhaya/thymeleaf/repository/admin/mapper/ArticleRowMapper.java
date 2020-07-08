package com.chhaya.thymeleaf.repository.admin.mapper;

import com.chhaya.thymeleaf.model.Article;
import com.chhaya.thymeleaf.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

        Article article = new Article();

        article.setId(rs.getInt("id"));
        article.setArticleId(rs.getString("article_id"));
        article.setTitle(rs.getString("title"));
        article.setDescription(rs.getString("description"));
        article.setThumbnail(rs.getString("thumbnail"));
        article.setAuthor(rs.getString("author"));
        article.setPublishedDate(rs.getDate("published_date"));
        article.setCategory(new Category(rs.getInt("category_id"), null, false));
        article.setStatus(rs.getBoolean("status"));

        return article;
    }

}
