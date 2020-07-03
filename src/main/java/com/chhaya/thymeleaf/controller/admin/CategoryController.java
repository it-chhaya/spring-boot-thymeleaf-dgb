package com.chhaya.thymeleaf.controller.admin;

import com.chhaya.thymeleaf.model.Category;
import com.chhaya.thymeleaf.service.admin.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private String CATEGORY_VIEW_NAME = "admin/category";

    private CategoryServiceImpl categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categoryView(@ModelAttribute Category category, ModelMap map) {

        map.addAttribute("category", category);

        return CATEGORY_VIEW_NAME;
    }

    @PostMapping
    public String saveCategoryAction(@Valid @ModelAttribute Category category,
                                     BindingResult result,
                                     RedirectAttributes redirect) {

        if (result.hasErrors()) {
            return CATEGORY_VIEW_NAME;
        }

        if (categoryService.save(category) != null) {
            redirect.addFlashAttribute("isSaved", true);
            redirect.addFlashAttribute("message", "Record is saved successfully");
        }

        return "redirect:/admin/categories";
    }

}
