package com.chhaya.thymeleaf.repository.admin.jdbc;

import com.chhaya.thymeleaf.model.Article;

import java.util.List;

public interface ArticleRepository {

    int save(Article article);

    List<Article> findAll();

    Article find(String articleId);

    int delete(String articleId);

    int update(Article newArticle);

}
