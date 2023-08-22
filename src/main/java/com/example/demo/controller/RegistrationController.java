package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
            @PathVariable String code){

    boolean isActivated = userService.activateUser(code);
    if(isActivated){
        model.addAttribute("message", "User successfully activated!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>This PizzaId exist! Get another PizzaId>>>>>>>>>>>>>>>>");

    }else {
        model.addAttribute("massage", "ActivationCode is not found!");
        System.out.println("::::::::::::::::::::This PizzaId exist! Get another PizzaId:::::::::::::::::::::");
    }

       return  "login";
    }


}
