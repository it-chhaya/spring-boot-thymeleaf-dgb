package com.chhaya.thymeleaf.service.admin;

import com.chhaya.thymeleaf.model.Article;

import java.util.List;

public interface ArticleService {

    Article save(Article article);

    List<Article> findAll();

    Article find(String articleId);

    void delete(String articleId);

    void update(Article newArticle);

}
