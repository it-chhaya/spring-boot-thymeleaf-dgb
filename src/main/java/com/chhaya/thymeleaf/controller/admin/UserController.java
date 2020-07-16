package com.chhaya.thymeleaf.controller.admin;

import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.service.admin.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final String ADD_USER_VIEW = "/admin/user-add";
    private final String USER_DETAILS_VIEW = "/admin/user-details";

    private final String ADD_USER_URL = "/admin/users/add";
    private final String USER_DETAILS_URL = "/admin/users";

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addUserView(User user,
                              ModelMap map) {

        map.addAttribute("user", user);

        return ADD_USER_VIEW;
    }

    @PostMapping("/add")
    public String addUserAction(@Valid User user,
                                BindingResult result,
                                RedirectAttributes redirect,
                                String confirm) {

        if (result.hasErrors()) {
            return ADD_USER_VIEW;
        }

        if (!user.getPassword().equals(confirm)) {
            redirect.addFlashAttribute("pwdError", true);
            redirect.addFlashAttribute("user", user);
            redirect.addFlashAttribute("message", "Password not match");
            return "redirect:" + ADD_USER_URL;
        }

        user.setUserId(UUID.randomUUID().toString());

        if (userService.save(user) != null) {
            redirect.addFlashAttribute("isSaved", true);
            redirect.addFlashAttribute("message", "Record is saved successfully");
        }

        return "redirect:" + USER_DETAILS_URL;
    }

    @GetMapping
    public String userView(ModelMap map) {


        map.addAttribute("users", userService.findAll());

        return USER_DETAILS_VIEW;
    }

    @GetMapping("/{userId}")
    public String deleteUserAction(@PathVariable String userId,
                                   RedirectAttributes redirect) {

        userService.deleteByUserId(userId);

        redirect.addFlashAttribute("isDeleted", true);
        redirect.addFlashAttribute("message", "Record is deleted successfully");

        return "redirect:" + USER_DETAILS_URL;
    }

    @GetMapping("/edit/{userId}")
    public String editUserView(@PathVariable String userId,
                                 ModelMap map) {

        map.addAttribute("user", userService.findOne(userId));
        map.addAttribute("isUpdate", true);

        return ADD_USER_VIEW;
    }

    @PostMapping("/edit/{userId}")
    public String editUserAction(@Valid User user,
                                 BindingResult bindingResult,
                                 ModelMap map,
                                 RedirectAttributes redirect) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("isUpdate", true);
            return ADD_USER_VIEW;
        }

        if (userService.updateByUserId(user) != null) {
            redirect.addFlashAttribute("isUpdated", true);
            redirect.addFlashAttribute("message", "The record is updated successfully");
        }

        return "redirect:" + USER_DETAILS_URL;
    }

}
