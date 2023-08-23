package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.service.CafeService;
import com.example.demo.service.PizzaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 */


@Controller
public class PizzaController {
    private final CafeService cafeService;
    private final PizzaService pizzaService;
    Iterable<Pizza> pizzas;
    Iterable<Cafe> cafes;

    public PizzaController(CafeService cafeService, PizzaService pizzaService) {
        this.cafeService = cafeService;
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

        Iterable<Cafe> cafes = cafeService.getAllCafes();
        model.addAttribute("cafes", cafes);
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("filter", filter);
        return "pizza";
    }


    @PostMapping("/pizza")
    @PreAuthorize(value = "hasAuthority('USER')")
    public String addPizza(
            @RequestParam String pizzaName,
            @RequestParam String size,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam("cafeId") Cafe cafe,
            @RequestParam Long cafeId,
            Model model) {
        if( !pizzaService.isCafeIdExistInCafe(cafeId))
        {

            model.addAttribute("message", "This CafeId exist! Get another CafeId");
            System.out.println("////////This CafeId exist! Get another CafeId////////////////");
            return "redirect:/pizza";
        } else {
            pizzaService.save(pizzaName, size, description, price, cafe);
        }
        Iterable<Pizza> pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("cafe", cafe);
        return "redirect:/pizza";
    }

    @GetMapping("/deletePizza{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePizza(
            Model model,
            @RequestParam(required = false, defaultValue = "") String id) {

        pizzaService.deletePizzaById(Long.valueOf(id));
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "redirect:/pizza";
    }


}
