package com.chhaya.thymeleaf.service.admin.impl;

import com.chhaya.thymeleaf.model.Authority;
import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.repository.admin.mybatis.UserRepository;
import com.chhaya.thymeleaf.service.admin.UserService;
import com.chhaya.thymeleaf.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll(Paging paging) {
        paging.setTotalCount(userRepository.countUser());
        return userRepository.findAll(paging);
    }

    @Override
    public User save(User user) {

        if (userRepository.save(user)) {
            if (userRepository.createUserRole(user)) {
                int roleId = user.getRoles().get(0).getId();
                for (Authority authority : user.getRoles().get(0).getAuthorities()) {
                    userRepository.createUserAuthority(roleId, authority.getId());
                }
                return user;
            }
        }

        return null;
    }

    @Override
    public User findOne(String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User updateByUserId(User newUser) {
        if (userRepository.updateByUserId(newUser))
            if (userRepository.updateUserRole(newUser))
                return newUser;
        return null;
    }

    @Override
    public void deleteByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public List<User> searchUserByKeyword(String keyword, Paging paging) {
        paging.setTotalCount(userRepository.countSearchResult(keyword));
        return userRepository.searchUserByKeyword(keyword, paging);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.selectUserByEmail(email);

    }

}
