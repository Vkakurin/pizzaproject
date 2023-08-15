package com.example.demo.controller;

import com.example.demo.model.enums.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * метод принимающий модель для ввода пользователей в браузер
     * @param model сама модель
     * @return вщзвращаем список пользователей
     */
    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";// возврвщаем view-файл в ресурсах,которй будет виден пользователю
    }

    @GetMapping("{user}")// параметр ,который будет указывать индеинтификатор пользователя
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }


    @PostMapping()
    public String userSave( // требуем параметры в методе
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);// меняем имя пользователя
        Set<String> roles = Arrays.stream((Role.values()))//помещаем в сэт роли пользователей и разворачиваем в строки
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();// очищаем роли пользоватея

        for (String key : form.keySet()) {// прверяем какие роли дали пользователям
            if (roles.contains(key)) {// добавляем в депозиторий роли
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.save(user);//добавляем в депозиторий изменения юзера с новой ролью  в бд
        return "redirect:/user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUserById(
            Model model,
            @PathVariable Long id) {

        userService.deleteById(id);
        model.addAttribute("user", userService.getAll());
        return "redirect:/user";
    }
}
