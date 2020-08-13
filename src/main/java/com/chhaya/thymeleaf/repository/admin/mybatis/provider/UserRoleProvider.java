package com.chhaya.thymeleaf.repository.admin.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserRoleProvider {

    public String createUserRoleSql() {
        return new SQL(){{
            INSERT_INTO("users_roles");
            VALUES("user_id", "#{id}");
            VALUES("role_id", "#{roles[0].id}");
        }}.toString();
    }

    public String updateUserRoleSql() {
        return new SQL(){{
            UPDATE("users_roles");
            SET("role_id = #{roles[0].id}");
            WHERE("user_id = #{id}");
        }}.toString();
    }

}
