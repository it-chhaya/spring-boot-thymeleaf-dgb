package com.chhaya.thymeleaf;

import com.chhaya.thymeleaf.model.User;
import com.chhaya.thymeleaf.repository.admin.mybatis.UserRepository;
import com.chhaya.thymeleaf.service.admin.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class ThymeleafApplicationTests {

    private UserServiceImpl userService;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

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



}
