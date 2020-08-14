package com.chhaya.thymeleaf.repository.admin.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserAuthorityProvider {

    public String createUserAuthoritySql() {

        return new SQL() {{

            INSERT_INTO("roles_authorities");
            VALUES("role_id", "#{roleId}");
            VALUES("authority_id", "#{authorityId}");

        }}.toString();

    }

    public String selectAuthoritiesByRoleIdSql() {

        return new SQL(){{

            SELECT("*");
            FROM("authorities a");
            INNER_JOIN("roles_authorities ra ON ra.authority_id = a.id");
            INNER_JOIN("users_roles ur ON ur.role_id = ra.role_id");
            WHERE("ra.role_id = #{roleId}");

        }}.toString();

    }

}
