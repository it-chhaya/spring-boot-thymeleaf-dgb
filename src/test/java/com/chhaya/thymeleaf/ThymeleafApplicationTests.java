package com.chhaya.thymeleaf;

import com.chhaya.thymeleaf.model.Authority;
import com.chhaya.thymeleaf.model.Role;
import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.repository.admin.mybatis.UserRepository;
import com.chhaya.thymeleaf.service.admin.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class ThymeleafApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {

        /*String testId = "13b85f22-0f54-48e0-b571-24b631bc089e";

        System.out.println(userService.findOne(testId));*/

        /*User user = new User();
        user.setUserId(testId);
        user.setFirstName("Chris");
        user.setLastName("Evan");

        userService.updateByUserId(user);*/


        /*User newUser = new User();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setFirstName("Tony");
        newUser.setLastName("Stark");
        newUser.setEmail("tony@gmail.com");
        newUser.setPassword("123");

        userService.save(newUser);*/
        /*List<User> users = userService.findAll();

        for (User user : users) {
            System.out.println(user);
        }*/
    }

    @Test
    void findUserById() {
        String userId = "13b85f22-0f54-48e0-b571-24b631bc089e";
        User user = userService.findOne(userId);
        System.out.println(user);
    }

    @Test
    void saveUser() {
        User newUser = new User();
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setFirstName("Leo");
        newUser.setLastName("Messi");
        newUser.setEmail("leomessi10@gmail.com");
        newUser.setPassword("123");

        User user = userService.save(newUser);

        if (user != null) {
            System.out.println("User is saved");
            System.out.println(user);
        } else
            System.out.println("User cannot saved");
    }

    @Test
    void updateUser() {
        User newUser = new User();
        newUser.setUserId("5a57eba0-5664-4c96-8631-210b37d16a01");
        newUser.setFirstName("Lionel");
        newUser.setLastName("Andre Messi");
        userRepository.updateByUserId(newUser);
    }

    @Test
    void selectUserByEmail() {
        System.out.println(userRepository.selectUserByEmail("it.chhaya@gmail.com"));
    }

    @Test
    void selectRolesById() {
        System.out.println(userRepository.selectRolesById(3));
    }

    @Test
    void generatePassword() {
        System.out.println(encoder.encode("123"));
    }

    @Test
    void createUserAuthority() {

        User user = new User();
        user.setUserId("87936382-4add-4e49-9dc8-32aa5c3d84ee");
        user.setFirstName("Hor");
        user.setLastName("Siekny");
        user.setEmail("horsiekny@gmail.com");
        user.setPassword("$2a$10$BzdbUIN2gi8MtI0GZXEyaumMDmozbPM.DmNGwu.pge8d1DG6nt/Km");

        List<Role> roleList = new ArrayList<>();
        List<Authority> authorityList = new ArrayList<>();

        Authority auth1 = new Authority();
        Authority auth2 = new Authority();
        Authority auth3 = new Authority();

        auth1.setId(1);
        auth2.setId(2);
        auth3.setId(3);

        authorityList.add(auth1);
        authorityList.add(auth2);
        authorityList.add(auth3);

        Role role = new Role();
        role.setId(1);
        role.setAuthorities(authorityList);

        roleList.add(role);

        user.setRoles(roleList);

        System.out.println("TEST USER = " + user);

        int roleId = user.getRoles().get(0).getId();

        for (Authority authority : user.getRoles().get(0).getAuthorities()) {
            boolean isInserted = userRepository.createUserAuthority(roleId, authority.getId());
            System.out.println("SUCCESS = " + isInserted);
            System.out.println("DONE");
        }

    }

    @Test
    void selectAuthoritiesByRoleId() {
        System.out.println("AUTHORITIES BY ROLE ID = " + userRepository.findOne("d0a0593c-9f9c-41e4-bc7e-9ee5767c5af9"));
    }

}
