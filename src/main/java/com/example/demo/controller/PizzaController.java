package com.example.demo.controller;

import com.example.demo.model.Pizza;
import com.example.demo.repos.PizzaRepo;
import com.example.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PizzaController {
    @Autowired
    private PizzaRepo pizzaRepo;
    private final PizzaService pizzaService;


    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;

    }

    @GetMapping("/pizza")
    public String getPizzaByName(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<Pizza> pizzas;
        if (filter != null && !filter.isEmpty()) {
            pizzas = pizzaRepo.findByPizzaName(filter);
        } else {
            pizzas = pizzaRepo.findAll();
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
        Pizza pizza = new Pizza(pizzaName, size, description, price);
        pizzaRepo.save(pizza);
        Iterable<Pizza> pizzas = pizzaRepo.findAll();
        model.addAttribute("pizzas", pizzas);
        return "redirect:/pizza";
    }

    @GetMapping("/deletePizza/{id}")
    public String deletePizza(
            Model model,
            @PathVariable Long id) {

        pizzaService.deletePizzaById(id);
        model.addAttribute("pizza", pizzaService.getAllPizzas());
        return "redirect:/pizza";
    }

    @GetMapping("/getPizza/{id}")
    public String getPizza(
            Model model,
            @PathVariable Long id
    ) {
        model.addAttribute("pizza", pizzaService.findPizzaById(id));
        model.addAttribute("id", id);
        return "redirect:/pizza";
    }


}
