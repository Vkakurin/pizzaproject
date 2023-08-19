package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.model.PizzaOrder;
import com.example.demo.service.CafeService;
import com.example.demo.service.PizzaOrderService;
import com.example.demo.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Method Post
     * Проверка наличия Пиццы-Кафе в листе заказа. Если есть, нельзя записать эту пиццу в Заказ, т.к. уже заказана.
     */

    @PostMapping("/pizzaOrder")
    public String addOrder(
            @RequestParam String nameCustomer,
            @RequestParam String addressDelivery,
            @RequestParam String phoneCustomer,
            @RequestParam("id") Pizza pizza,
            @RequestParam(required = false, defaultValue = "") Long id,
            Model model
    ) {


        if(! (!pizzaOrderService.isPizzaIdExistInOrders(id) && pizzaOrderService.isPizzaIdExistInPizza(id) ) ){
            model.addAttribute("message", "This PizzaId exist! Get another PizzaId");
            return "redirect:/pizzaOrder";
        } else {
            pizzaOrderService.save(nameCustomer, addressDelivery, phoneCustomer, pizza);
        }
        pizzaOrders = pizzaOrderService.getAllOrders();
            model.addAttribute("pizzaOrders", pizzaOrders);
            model.addAttribute("pizzas", pizzas);

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


    @GetMapping("/getOrder{id}")
    public String getOrder(
            Model model,
            @PathVariable Long id
    ) {
        model.addAttribute("pizzaOrder", pizzaOrderService.getAllOrders());

        return "redirect:/pizzaOrder";
    }

//    public boolean isPizzaIdExist(Long id) {
//        boolean flag =false;
//        for (PizzaOrder p : pizzaOrders) {
//            Long test = p.getPizza().getId();
//            if (id == test) {
//                return true;
//            }
//        }
//        return flag;
//    }

}
