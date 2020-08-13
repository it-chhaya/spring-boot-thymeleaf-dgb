package com.chhaya.thymeleaf.service.admin;

import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.utils.Paging;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll(Paging paging);

    User save(User user);

    User findOne(String userId);

    User updateByUserId(User newUser);

    void deleteByUserId(String userId);

    List<User> searchUserByKeyword(String keyword, Paging paging);

}
