package com.example.demo.serviceTest;


import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repos.UserRepo;
import com.example.demo.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceSaveTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;


    @Test
    public void addUser() {
        User user = new User();
        user.setUser_id(100L);
        user.setUsername("cust12345");
        user.setPassword("$2a$08$9GhMLlm61Xs6uHtgRNuF2eI/LFW.rXgkKe2ymj7bh6AlfQTsDUusO");
        user.setActivationCode("test");

        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertNotNull(user.getPassword());
        Assert.assertNotEquals("password", "$2a$08$9GhMLlm61Xs6uHtgRNuF2eI/LFW.rXgkKe2ymj7bh6AlfQTsDUusO", user.getPassword());
        Assert.assertNotNull(user.getUsername());
        Assert.assertEquals("test", "cust12345", user.getUsername());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        Assert.assertTrue(user.isActive());


    }








}







