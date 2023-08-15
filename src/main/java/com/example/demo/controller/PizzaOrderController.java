package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.model.PizzaOrder;
import com.example.demo.service.PizzaOrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class PizzaOrderController {


    private final PizzaOrderService pizzaOrderService;

    public PizzaOrderController(PizzaOrderService pizzaOrderService) {
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
        model.addAttribute("pizzaOrders", orders);
        model.addAttribute("filter", filter);
        return "pizzaOrder";
    }


    @PostMapping("/pizzaOrder")
    public String addOrder(

            @RequestParam String nameCustomer,
            @RequestParam String addressDelivery,
            @RequestParam String phoneCustomer,
            @AuthenticationPrincipal Pizza pizza,
            @AuthenticationPrincipal Cafe cafe,
            Model model) {
        pizzaOrderService.save(nameCustomer, addressDelivery, phoneCustomer, pizza, cafe);
        Iterable<PizzaOrder> pizzaOrders = pizzaOrderService.getAllOrders();
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
