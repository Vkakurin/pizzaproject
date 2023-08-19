package com.example.demo.service;


import com.example.demo.model.Pizza;
import com.example.demo.model.PizzaOrder;
import com.example.demo.repos.PizzaOrderRepo;
import com.example.demo.repos.PizzaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PizzaOrderService {

    private final PizzaOrderRepo pizzaOrderRepo;
    private final PizzaRepo pizzaRepo;


    public PizzaOrderService(PizzaOrderRepo pizzaOrderRepo, PizzaRepo pizzaRepo) {
        this.pizzaOrderRepo = pizzaOrderRepo;
        this.pizzaRepo = pizzaRepo;
    }


    public void deleteOrderById(Long id) {
        pizzaOrderRepo.deleteById(id);
    }

    public List<PizzaOrder> getAllOrders() {
        return StreamSupport.stream(pizzaOrderRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void save(String nameCustomer,
                     String addressDelivery,
                     String phoneCustomer,
                     Pizza pizza
    ) {
        pizzaOrderRepo.save(new PizzaOrder(nameCustomer, addressDelivery, phoneCustomer, pizza));
    }

    public List<PizzaOrder> findPizzaByName(String filter) {
        return pizzaOrderRepo.findByNameCustomer(filter);
    }

    /**
     * Проверка наличия Пиццы-Кафе в листе заказа.
     *
     * @param id параметр номера Пицца-Кафе, которую выбирает Customer
     * @return false , если Пицца-Кафе нет в Заказе.
     * Если есть, нельзя сохранить эту пиццу в Заказ, т.к. уже заказана.
     * проверка по введенному id : есть ли Id Пиццы-Кафе в списке Пиццы-Кафе
     */
    public boolean isPizzaIdExistInOrders(Long id) {
        boolean flag = false;
        List<PizzaOrder> pizzaOrders = getAllOrders();
        for (PizzaOrder p : pizzaOrders) {
            Long pizzaIdExistInOrder = p.getPizza().getId();
            if (Objects.equals(id, pizzaIdExistInOrder)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public boolean isPizzaIdExistInPizza(Long id) {

        return pizzaRepo.existsById(id);
    }

}
