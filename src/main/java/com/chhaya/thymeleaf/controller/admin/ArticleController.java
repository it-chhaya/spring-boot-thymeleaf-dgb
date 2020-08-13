package com.chhaya.thymeleaf.controller.admin;

import com.chhaya.thymeleaf.model.Article;
import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.service.admin.impl.ArticleServiceImpl;
import com.chhaya.thymeleaf.service.admin.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.UUID;

@Controller
@RequestMapping("admin/articles")
public class ArticleController {

    private final String MAIN_URL = "/admin/articles";
    private final String ARTICLE_VIEW_PATH = "admin/article";

    @Value("${file.server-path}")
    private String serverPath;

    private ArticleServiceImpl articleService;
    private CategoryServiceImpl categoryService;

    @Autowired
    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCategoryService(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String articleView(ModelMap map,
                              @ModelAttribute Article article) {

        map.addAttribute("categories", categoryService.findAll());
        map.addAttribute("article", article);
        map.addAttribute("articleList", articleService.findAll());

        return ARTICLE_VIEW_PATH;
    }

    @PostMapping
    public String saveArticleAction(@ModelAttribute Article article,
                                    @RequestParam("file") MultipartFile file) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User authUser = ((User) authentication.getPrincipal());

        String fileName = file.getOriginalFilename();
        String uri = UUID.randomUUID() + fileName.substring(fileName.indexOf("."));

        try {
            Files.copy(file.getInputStream(), Paths.get(serverPath + uri));
        } catch (IOException e) {
            e.printStackTrace();
        }

        article.setArticleId(UUID.randomUUID().toString());
        article.setAuthor(authUser.getFirstName() + " " + authUser.getLastName());
        article.setPublishedDate(new Date(System.currentTimeMillis()));
        article.setThumbnail(uri);

        articleService.save(article);

        return "redirect:" + MAIN_URL;
    }

    @PostMapping("/{articleId}")
    public String editArticleAction(@ModelAttribute Article article,
                                    @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String uri = UUID.randomUUID() + fileName.substring(fileName.indexOf("."));
            try {
                Files.copy(file.getInputStream(), Paths.get(serverPath + uri));
            } catch (IOException e) {
                e.printStackTrace();
            }
            article.setThumbnail(uri);
        }

        articleService.update(article);

        return "redirect:" + MAIN_URL;
    }

}
