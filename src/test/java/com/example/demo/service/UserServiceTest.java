package com.example.demo.service;

import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.UUID;

import static com.example.demo.model.enums.Role.USER;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    private static String userIdTest = "118";// Смотреть существует ли с таким номером в  БД перед запуском Общего теста !!!!!
    // Если нечего удалять можно запустить несколько тестовых Save.
    // !!!!Внимание не удалять user12(HE НАБИРАТЬ ЕГО ID ) ,тк у него роль 'ADMIN" с доступом в таблицу 'USR'
    static int expectSizeUsers = 6;// Смотреть актуальное количество записей в БД перед запуском Общего теста !!!!!
    private static String userNameTest = "userTest";
    private String userNameTestForSave = "admin234Test";



    @Test
    void getAll() {
        Assert.assertEquals(expectSizeUsers, userService.getAll().size());
    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setUsername(userNameTest);
        userService.loadUserByUsername(String.valueOf(user));
        Assert.assertEquals(userNameTest, user.getUsername());
    }

    @Test
    void isUserIdExist() {
        String existUserId = "19";
        String notExistUserId = "1";
        Assert.assertTrue(userService.isUserIdExist(existUserId));
        Assert.assertFalse(userService.isUserIdExist(notExistUserId));
    }

    @Test
    void save() {
        User userSave = new User();
        userSave.setUser_id(Long.valueOf(userIdTest));
        userSave.setUsername(userNameTestForSave);
        userSave.setActive(true);
        userSave.setPassword("234Test");
        userSave.setPassword2(null);
        userSave.setEmail("some@mail.ru");
        userSave.setActivationCode(UUID.randomUUID().toString());
        userSave.setRoles(Collections.singleton(USER));
        userService.save(userSave);
        Assert.assertNotNull( userSave.getUser_id());
        Assert.assertEquals(userNameTestForSave, userSave.getUsername());
        Assert.assertNotNull( userSave.getPassword());
        Assert.assertEquals("some@mail.ru", userSave.getEmail());
        Assert.assertTrue("USER",userSave.getRoles().contains(USER));
        Assert.assertEquals(null, userSave.getPassword2());
        Assert.assertNotNull(userSave.getActivationCode());
        Assert.assertTrue(userSave.isActive());
    }

    @Test
    void deleteById() {
        Long userIdForDelete = Long.valueOf(userIdTest);
        userService.deleteById(userIdForDelete);
        Assert.assertFalse(String.valueOf(userIdForDelete),userService.isUserIdExist(String.valueOf(userIdForDelete)));
    }

    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername(userNameTest);
        userService.findByUsername(user);
        Assert.assertEquals(userNameTest, user.getUsername());
    }

    @Test
    void activateUser() {
        String testCode = "135c550c-0f59-43a1-a1c4-74612793fc46";
        User user = new User();
        user.setActivationCode(testCode);

        Assert.assertFalse(userService.activateUser(testCode));

    }
}