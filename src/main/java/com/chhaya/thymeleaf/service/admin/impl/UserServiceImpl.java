package com.chhaya.thymeleaf.service.admin.impl;

import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.repository.admin.mybatis.UserRepository;
import com.chhaya.thymeleaf.service.admin.UserService;
import com.chhaya.thymeleaf.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user) ? user : null;
    }

    @Override
    public User findOne(String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User updateByUserId(User newUser) {
        return userRepository.updateByUserId(newUser) ? newUser : null;
    }

    @Override
    public void deleteByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }
}
