package com.example.demo.controller;

import com.example.demo.model.enums.Role;
import com.example.demo.model.User;
import com.example.demo.repos.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userService.findByUsername(user);
        if (userFromDb != null) {
            model.addAttribute("message", "User exist!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));// назначаем роль
        //user.setCode(UUID.randomUUID().toString();
        //user.setPassword(passwordEncoder.encoder(user.getPassword()));//назначаем пользователю зашифрованный пароль
     //  String message = String.format("Hello<%\n" +
      //         "Привет")






        userService.save(user);
        return "redirect:/login";
    }

}
