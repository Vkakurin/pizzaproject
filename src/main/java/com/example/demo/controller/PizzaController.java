package com.example.demo.controller;

import com.example.demo.model.Pizza;
import com.example.demo.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PizzaController {

    private final PizzaService pizzaService;
    Iterable<Pizza> pizzas;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;

    }

    @GetMapping("/pizza")
    public String getPizzaByName(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {

        if (filter != null && !filter.isEmpty()) {
            pizzas = pizzaService.findPizzaByName(filter);
        } else {
            pizzas = pizzaService.getAllPizzas();
        }
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("filter", filter);
        return "pizza";
    }


    @PostMapping("/pizza")
    public String addPizza(
            @RequestParam String pizzaName,
            @RequestParam String size,
            @RequestParam String description,
            @RequestParam Double price,
            Model model) {
        pizzaService.save(pizzaName, size, description, price);
        Iterable<Pizza> pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzas", pizzas);

        return "redirect:/pizza";
    }

    @GetMapping("/deletePizza{id}")
    public String deletePizza(
            Model model,
            @RequestParam(required = false, defaultValue = "") String id) {

        pizzaService.deletePizzaById(Long.valueOf(id));
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "redirect:/pizza";
    }


}
