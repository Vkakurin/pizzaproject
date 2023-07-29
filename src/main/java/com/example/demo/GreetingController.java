package com.example.demo;

import com.example.demo.domain.Pizza;
import com.example.demo.repos.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    private final PizzaRepo pizzaRepo;

    public GreetingController(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }


    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Pizza> pizzas = pizzaRepo.findAll();
        model.put("pizzaName", pizzas);
        return "main";
    }

    @PostMapping
    public String add(
            @RequestParam String pizzaName,
            @RequestParam String size,
            @RequestParam String description,
            Map<String, Object> model) {
        Pizza pizza = new Pizza(pizzaName, size, description);
        pizzaRepo.save(pizza);
        Iterable<Pizza> pizzas = pizzaRepo.findAll();
        model.put("pizzaName", pizzas);
        return "main";
    }
    @PostMapping("filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model
    ){
        List<Pizza> pizzas = pizzaRepo.findPizzaByPizzaName(filter);
        model.put("pizzas", pizzas);
   return "main";
    }

}
