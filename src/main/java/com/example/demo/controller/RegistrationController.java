package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    public String addUser( @Valid User user,
                           BindingResult bindingResult,
                          Model model) {

        if(user.getPassword() != null && !user.getPassword().equals(user.getPassword2())){
            model.addAttribute("message", "*****Passwords are different!*****");
        return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("message", "*****User exist!*****");
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

       return "login";
    }


}
