package com.chhaya.thymeleaf.service.admin.impl;

import com.chhaya.thymeleaf.model.Article;
import com.chhaya.thymeleaf.repository.admin.impl.ArticleRepositoryImpl;
import com.chhaya.thymeleaf.service.admin.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepositoryImpl articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepositoryImpl articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article) == 1 ? article : null;
    }

    @Override
    public List<Article> findAll() {
        System.out.println(articleRepository.findAll());
        return articleRepository.findAll();
    }

    @Override
    public Article find(String articleId) {
        return null;
    }

    @Override
    public void delete(String articleId) {

    }

    @Override
    public void update(Article newArticle) {
        articleRepository.update(newArticle);
    }
}
