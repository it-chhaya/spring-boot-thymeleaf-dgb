package com.chhaya.thymeleaf.service.admin.impl;

import com.chhaya.thymeleaf.model.Authority;
import com.chhaya.thymeleaf.repository.admin.mybatis.AuthorityRepository;
import com.chhaya.thymeleaf.service.admin.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> select() {
        return authorityRepository.select();
    }

}
