package com.chhaya.thymeleaf.controller.admin;

import com.chhaya.thymeleaf.model.Authority;
import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.service.admin.impl.AuthorityServiceImpl;
import com.chhaya.thymeleaf.service.admin.impl.RoleServiceImpl;
import com.chhaya.thymeleaf.service.admin.impl.UserServiceImpl;
import com.chhaya.thymeleaf.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final String ADD_USER_VIEW = "/admin/user-add";
    private final String USER_DETAILS_VIEW = "/admin/user-details";

    private final String ADD_USER_URL = "/admin/users/add";
    private final String USER_DETAILS_URL = "/admin/users";

    private UserServiceImpl userService;

    private RoleServiceImpl roleService;

    private AuthorityServiceImpl authorityService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService, AuthorityServiceImpl authorityService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authorityService = authorityService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/add")
    public String addUserView(User user,
                              ModelMap map) {

        List<Authority> authorities = authorityService.select();

        map.addAttribute("user", user);
        map.addAttribute("roles", roleService.select());
        map.addAttribute("auths", authorities);

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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        System.out.println("USER = " + user);

        if (userService.save(user) != null) {
            redirect.addFlashAttribute("isSaved", true);
            redirect.addFlashAttribute("message", "Record is saved successfully");
        }

        return "redirect:" + USER_DETAILS_URL;
    }

    @GetMapping
    public String userView(ModelMap map,
                           Paging paging,
                           @RequestParam(required = false) String keyword) {

        List<User> users;

        if (keyword == null) {
            users = userService.findAll(paging);
        } else {
            users = userService.searchUserByKeyword(keyword, paging);
        }

        map.addAttribute("paging", paging);
        map.addAttribute("users", users);
        map.addAttribute("keyword", keyword);

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

        System.out.println(userService.findOne(userId));

        map.addAttribute("user", userService.findOne(userId));
        map.addAttribute("roles", roleService.select());
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

        System.out.println("My user = " + user);

        if (userService.updateByUserId(user) != null) {
            redirect.addFlashAttribute("isUpdated", true);
            redirect.addFlashAttribute("message", "The record is updated successfully");
        }

        return "redirect:" + USER_DETAILS_URL;
    }

}
