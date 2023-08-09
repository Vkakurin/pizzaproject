package com.example.demo.controller;

import com.example.demo.model.PizzaOrder;
import com.example.demo.repos.PizzaOrderRepo;
import com.example.demo.service.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class PizzaOrderController {
    @Autowired
    private PizzaOrderRepo orderRepo;

    private final PizzaOrderService orderService;

    public PizzaOrderController(PizzaOrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/pizzaOrder")
    public String getOrder(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<PizzaOrder> orders;
        if (filter != null && !filter.isEmpty()) {
            orders = orderRepo.findByNameCustomer(filter);
        } else {
           orders = orderRepo.findAll();
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
            Model model) {
        PizzaOrder pizzaOrder = new PizzaOrder(nameCustomer, addressDelivery, phoneCustomer);
        orderRepo.save(pizzaOrder);
        Iterable<PizzaOrder> pizzaOrders = orderRepo.findAll();
        model.addAttribute("pizzaOrders", pizzaOrders);
        return "redirect:/pizzaOrder";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(
            Model model,
            @PathVariable Long id) {

        orderService.deleteOrderById(id);
        model.addAttribute("pizzaOrder", orderService.getAllOrders());
        return "redirect:/pizzaOrder";
    }

    @GetMapping("/getOrder/{id}")
    public String getOrder(
            Model model,
            @PathVariable Long id
    ) {
        model.addAttribute("pizzaOrder", orderService.getAllOrders());

        return "redirect:/pizzaOrder";
    }


}
