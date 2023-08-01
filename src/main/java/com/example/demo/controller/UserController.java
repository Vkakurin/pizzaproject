package com.example.demo.controller;

import com.example.demo.model.enums.Role;
import com.example.demo.model.User;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
       model.addAttribute("users",userRepo.findAll());

        return "userList";
    }
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("UserId") User user
    ){
        user.setUsername(username);
        Set<String> roles = Arrays.stream((Role.values()))
                        .map(Role::name)
                        .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key: form.keySet()) {
            if(roles.contains(key)) {
            user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/user";
    }

}
