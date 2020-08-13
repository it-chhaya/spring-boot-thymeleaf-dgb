package com.chhaya.thymeleaf.service.admin.impl;

import com.chhaya.thymeleaf.model.Role;
import com.chhaya.thymeleaf.repository.admin.mybatis.RoleRepository;
import com.chhaya.thymeleaf.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> select() {
        return roleRepository.select();
    }

}
