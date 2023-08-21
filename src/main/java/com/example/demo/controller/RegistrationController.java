package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exist!");
            return "registration";
        }

        return "login";
    }
    @GetMapping("/activate/{code}")
    public String activate(
            Model model,
            @RequestParam(required = false, defaultValue = "") String code){

    boolean isActivated = userService.activateUser(code);
    if(isActivated){
        model.addAttribute("message", "User successfully activated!");
    }else {
        model.addAttribute("massage", "ActivationCode is not found!");
    }

       return  "registration";
    }


}
