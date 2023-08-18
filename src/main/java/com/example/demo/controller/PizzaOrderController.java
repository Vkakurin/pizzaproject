package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.model.PizzaOrder;
import com.example.demo.model.User;
import com.example.demo.repos.PizzaRepo;
import com.example.demo.service.CafeService;
import com.example.demo.service.PizzaOrderService;
import com.example.demo.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Spliterator;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller

public class PizzaOrderController {

    private final PizzaService pizzaService;
    private final CafeService cafeService;
    private final PizzaOrderService pizzaOrderService;
    Iterable<PizzaOrder> pizzaOrders;
    Iterable<Pizza> pizzas;
    Iterable<Cafe> cafes;

    public PizzaOrderController(PizzaService pizzaService, CafeService cafeService, PizzaOrderService pizzaOrderService) {
        this.pizzaService = pizzaService;
        this.cafeService = cafeService;
        this.pizzaOrderService = pizzaOrderService;
    }


    @GetMapping("/pizzaOrder")
    public String getOrder(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {

        if (filter != null && !filter.isEmpty()) {
            pizzaOrders = pizzaOrderService.findPizzaByName(filter);
        } else {
            pizzaOrders = pizzaOrderService.getAllOrders();
        }
        pizzas = pizzaService.getAllPizzas();
        cafes = cafeService.getAllCafes();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("pizzaOrders", pizzaOrders);
        model.addAttribute("filter", filter);
        model.addAttribute("cafes", cafes);
        return "pizzaOrder";
    }

    //todo
    @PostMapping("/pizzaOrder")
    public String addOrder(
            @RequestParam String nameCustomer,
            @RequestParam String addressDelivery,
            @RequestParam String phoneCustomer,
            @RequestParam("id") Pizza pizza,
            @RequestParam(required = false, defaultValue = "") Long id,
            Model model
    ) {
        pizzaOrders = pizzaOrderService.getAllOrders();
        System.out.println("//////////////" + pizza.getId() + "/////////////" + pizza);
        if (id == id) {
            System.out.println("------------------"+ pizzaOrders);
            model.addAttribute("message", "This PizzaId exist! Get another PizzaId");
            model.addAttribute("pizzaOrders", pizzaOrders);
            return "redirect:/pizzaOrder";

        } else {
            pizzaOrderService.save(nameCustomer, addressDelivery, phoneCustomer, pizza);
        }

        pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzaOrders", pizzaOrders);
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("pizza", pizza);

        return "redirect:/pizzaOrder";
    }

    @GetMapping("/deleteOrder{order_id}")
    public String deleteOrder(
            Model model,
            @RequestParam(required = false, defaultValue = "") String order_id) {

        pizzaOrderService.deleteOrderById(Long.valueOf(order_id));
        model.addAttribute("pizzaOrder", pizzaOrderService.getAllOrders());
        return "redirect:/pizzaOrder";
    }

    //todo
    @GetMapping("/getOrder{id}")
    public String getOrder(
            Model model,
            @PathVariable Long id
    ) {
        model.addAttribute("pizzaOrder", pizzaOrderService.getAllOrders());

        return "redirect:/pizzaOrder";
    }


}
