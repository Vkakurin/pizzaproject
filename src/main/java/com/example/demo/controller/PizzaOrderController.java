package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.model.PizzaOrder;
import com.example.demo.service.CafeService;
import com.example.demo.service.PizzaOrderService;
import com.example.demo.service.PizzaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class PizzaOrderController {

    private final PizzaService pizzaService;
    private final CafeService cafeService;
    private final PizzaOrderService pizzaOrderService;

    public PizzaOrderController(PizzaService pizzaService, CafeService cafeService, PizzaOrderService pizzaOrderService) {
        this.pizzaService = pizzaService;
        this.cafeService = cafeService;
        this.pizzaOrderService = pizzaOrderService;
    }


    @GetMapping("/pizzaOrder")
    public String getOrder(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<PizzaOrder> orders;
        if (filter != null && !filter.isEmpty()) {
            orders = pizzaOrderService.findPizzaByName(filter);
        } else {
            orders = pizzaOrderService.getAllOrders();
        }
        Iterable<Pizza> pizzas = pizzaService.getAllPizzas();
        Iterable<Cafe> cafes = cafeService.getAllCafes();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("cafes", cafes);
        model.addAttribute("pizzaOrders", orders);
        model.addAttribute("filter", filter);
        return "pizzaOrder";
    }


    @PostMapping("/pizzaOrder")
    public String addOrder(
//            @RequestParam(required = false, defaultValue = "") String id,
//            @RequestParam(required = false, defaultValue = "") String cafeId,
            @RequestParam String nameCustomer,
            @RequestParam String addressDelivery,
            @RequestParam String phoneCustomer,
            @RequestParam("id") Pizza pizza,
            @RequestParam("cafeId") Cafe cafe,
            Model model) {
        pizzaOrderService.save(nameCustomer, addressDelivery, phoneCustomer, pizza, cafe);
        Iterable<PizzaOrder> pizzaOrders = pizzaOrderService.getAllOrders();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+pizzaOrders);
        model.addAttribute("pizzaOrders", pizzaOrders);

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
