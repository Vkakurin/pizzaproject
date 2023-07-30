package com.example.demo;

import com.example.demo.domain.Pizza;
import com.example.demo.repos.PizzaRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Controller
public class GreetingController {

    private final PizzaRepo pizzaRepo;

    public GreetingController(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }


    @GetMapping("")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Pizza> pizzas = pizzaRepo.findAll();
        model.put("pizzas", pizzas);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam String pizzaName,
            @RequestParam String size,
            @RequestParam String description,
            Map<String, Object> model) {
        Pizza pizza = new Pizza(pizzaName, size, description);
        pizzaRepo.save(pizza);
        Iterable<Pizza> pizzas = pizzaRepo.findAll();
        model.put("pizzas", pizzas);
        return "main";
    }

    @PostMapping("filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model
    ) {
        Iterable<Pizza> pizzas;
        if (filter != null) {
            pizzas = pizzaRepo.findByPizzaName(filter);
        } else {
            pizzas = pizzaRepo.findAll();
        }
        model.put("pizzas", pizzas);
        return "main";
    }

    //todo

    @DeleteMapping("delete/{id}")
    public String deletePizza(
            Map<String, Object> model,
            @PathVariable Long id){

        pizzaRepo.deleteById(id);
        model.put("pizzas",deletePizza(model, id));
        return "main";
    }


}
