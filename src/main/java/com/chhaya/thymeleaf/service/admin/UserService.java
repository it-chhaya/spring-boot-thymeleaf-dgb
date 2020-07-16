package com.chhaya.thymeleaf.service.admin;

import com.chhaya.thymeleaf.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    User findOne(String userId);

    User updateByUserId(User newUser);

    void deleteByUserId(String userId);

}
