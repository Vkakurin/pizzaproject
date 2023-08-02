package com.example.demo.controller;

import com.example.demo.model.Pizza;
import com.example.demo.repos.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private PizzaRepo pizzaRepo;

    @GetMapping("")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Map<String, Object> model) {
        Iterable<Pizza> pizzas;
        if (filter != null && !filter.isEmpty()) {
            pizzas = pizzaRepo.findByPizzaName(filter);
        } else {
            pizzas = pizzaRepo.findAll();
        }
        model.put("pizzas", pizzas);
        model.put("filter", filter);
        return "main";
    }
//
//    @PostMapping("/main")
//    public String add(
//            @RequestParam String pizzaName,
//            @RequestParam String size,
//            @RequestParam String description,
//            Map<String, Object> model) {
//        Pizza pizza = new Pizza(pizzaName, size, description);
//        pizzaRepo.save(pizza);
//        Iterable<Pizza> pizzas = pizzaRepo.findAll();
//        model.put("pizzas", pizzas);
//        return "main";
//    }



}
