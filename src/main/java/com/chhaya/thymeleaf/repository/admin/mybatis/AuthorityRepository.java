package com.chhaya.thymeleaf.repository.admin.mybatis;

import com.chhaya.thymeleaf.model.Authority;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository {

    @Select("SELECT id, name FROM authorities")
    List<Authority> select();

}
