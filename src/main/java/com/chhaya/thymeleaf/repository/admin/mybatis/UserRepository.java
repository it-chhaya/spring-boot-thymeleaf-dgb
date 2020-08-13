package com.chhaya.thymeleaf.repository.admin.mybatis;

import com.chhaya.thymeleaf.model.Role;
import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.repository.admin.mybatis.provider.UserProvider;
import com.chhaya.thymeleaf.repository.admin.mybatis.provider.UserRoleProvider;
import com.chhaya.thymeleaf.utils.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    /*@Update("UPDATE users SET " +
            "first_name = #{firstName}," +
            "last_name = #{lastName} " +
            "WHERE user_id = #{userId}")*/
    @UpdateProvider(type = UserProvider.class, method = "updateUserSql")
    boolean updateByUserId(User newUser);

    @Update("UPDATE users SET " +
            "status = FALSE " +
            "WHERE user_id = #{userId}")
    void deleteByUserId(String userId);

    //@Select("SELECT * FROM users WHERE status = true")
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

    /*@Select("SELECT * FROM users " +
            "WHERE user_id = #{userId} AND status = true")*/
    @SelectProvider(type = UserProvider.class, method = "selectUserByUserIdSql")
    @ResultMap("userResult")
    User findOne(String userId);

    /*@Insert("INSERT INTO users (user_id, first_name, last_name, email, password) " +
            "VALUES (#{userId}, #{firstName}, #{lastName}, #{email}, #{password})")*/
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
    List<Role> selectRolesById(int id);

    @InsertProvider(type = UserRoleProvider.class, method = "createUserRoleSql")
    boolean createUserRole(User user);

    @UpdateProvider(type = UserRoleProvider.class, method = "updateUserRoleSql")
    boolean updateUserRole(User user);

}
