package com.chhaya.thymeleaf.repository.admin.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class RoleProvider {

    public String selectRoleSql() {
        return new SQL(){{
            SELECT("*");
            FROM("roles");
        }}.toString();
    }

}
