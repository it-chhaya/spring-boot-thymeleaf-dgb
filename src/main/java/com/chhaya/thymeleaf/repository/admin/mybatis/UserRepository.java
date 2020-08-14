package com.chhaya.thymeleaf.repository.admin.mybatis;

import com.chhaya.thymeleaf.model.Authority;
import com.chhaya.thymeleaf.model.Role;
import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.repository.admin.mybatis.provider.UserAuthorityProvider;
import com.chhaya.thymeleaf.repository.admin.mybatis.provider.UserProvider;
import com.chhaya.thymeleaf.repository.admin.mybatis.provider.UserRoleProvider;
import com.chhaya.thymeleaf.utils.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    @UpdateProvider(type = UserProvider.class, method = "updateUserSql")
    boolean updateByUserId(User newUser);


    @Update("UPDATE users SET " +
            "status = FALSE " +
            "WHERE user_id = #{userId}")
    void deleteByUserId(String userId);


    @SelectProvider(type = UserProvider.class, method = "selectUserSql")
    @Results(
            id = "userResult",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "firstName", column = "first_name"),
                    @Result(property = "lastName", column = "last_name"),
                    @Result(property = "roles", column = "id",
                    many = @Many(select = "selectRolesById"))
            })
    List<User> findAll(Paging paging);


    @SelectProvider(type = UserProvider.class, method = "selectUserByUserIdSql")
    @ResultMap("userResult")
    User findOne(String userId);


    @InsertProvider(type = UserProvider.class, method = "insertUserSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean save(User user);


    @Select("SELECT COUNT(*) FROM users WHERE status = true")
    int countUser();


    @SelectProvider(type = UserProvider.class, method = "searchUserByKeywordSql")
    @ResultMap("userResult")
    List<User> searchUserByKeyword(@Param("keyword") String keyword, Paging paging);


    @SelectProvider(type = UserProvider.class, method = "countSearchResultSql")
    int countSearchResult(@Param("keyword") String keyword);


    @SelectProvider(type = UserProvider.class, method = "selectUserByEmailSql")
    @ResultMap("userResult")
    User selectUserByEmail(String email);


    @SelectProvider(type = UserProvider.class, method = "selectRolesByIdSql")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "authorities", column = "id", many = @Many(select = "selectAuthoritiesByRoleId"))
    })
    List<Role> selectRolesById(int id);


    @InsertProvider(type = UserRoleProvider.class, method = "createUserRoleSql")
    boolean createUserRole(User user);


    @UpdateProvider(type = UserRoleProvider.class, method = "updateUserRoleSql")
    boolean updateUserRole(User user);


    @InsertProvider(type = UserAuthorityProvider.class, method = "createUserAuthoritySql")
    boolean createUserAuthority(int roleId, int authorityId);

    @SelectProvider(type = UserAuthorityProvider.class, method = "selectAuthoritiesByRoleIdSql")
    List<Authority> selectAuthoritiesByRoleId(int roleId);

}
