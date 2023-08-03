package com.example.demo.controller;

import com.example.demo.model.Pizza;
import com.example.demo.repos.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Controller
public class PizzaController {
    @Autowired
    private PizzaRepo pizzaRepo;

    @GetMapping("/pizza")
    public String getPizza(
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
        return "pizza";
    }

    @PostMapping("/pizza")
    public String addPizza(
            @RequestParam String pizzaName,
            @RequestParam String size,
            @RequestParam String description,
            Map<String, Object> model) {
        Pizza pizza = new Pizza(pizzaName, size, description);
        pizzaRepo.save(pizza);
        Iterable<Pizza> pizzas = pizzaRepo.findAll();
        model.put("pizzas", pizzas);
        return "redirect:/pizza";
    }


    //todo

   @GetMapping("/deletePizza/{id}")
    public String deletePizza(
            Model model,
            @PathVariable Long id){

        pizzaRepo.deleteById(id);
        model.addAttribute("pizzas", pizzaRepo.findAll());
        return "pizza";
    }


}
