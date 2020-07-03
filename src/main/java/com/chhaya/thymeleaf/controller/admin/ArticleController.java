package com.chhaya.thymeleaf.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/articles")
public class ArticleController {

    @GetMapping
    public String articleView() {
        return "admin/article";
    }

}
