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


/**
 * Service CRUD Methods. I can use service for PizzaOrderController via Repository.
 */
@Service
public class PizzaOrderService {

    private final PizzaOrderRepo pizzaOrderRepo;
    private final PizzaRepo pizzaRepo;


    public PizzaOrderService(PizzaOrderRepo pizzaOrderRepo, PizzaRepo pizzaRepo) {
        this.pizzaOrderRepo = pizzaOrderRepo;
        this.pizzaRepo = pizzaRepo;
    }

    /**
     * Method delete records PizzaOrder by "orderId" from pizzaOrderRepo.
     * @param
     */
    public void deleteOrderById(Long id) {
        pizzaOrderRepo.deleteById(id);
    }

    /**
     * Method get into the List all records PizzaOrder  from PizzaOrderRepo.
     * @param
     */
    public List<PizzaOrder> getAllOrders() {
        return StreamSupport.stream(pizzaOrderRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Method put records to new PizzaOrder into PizzaOrderRepo.
     * @param
     */
    public void save(String nameCustomer,
                     String addressDelivery,
                     String phoneCustomer,
                     Pizza pizza

    ) {
        pizzaOrderRepo.save(new PizzaOrder(nameCustomer, addressDelivery, phoneCustomer, pizza));
    }

    /***
     * Method to search orders Customer by his nameCustomer.
     * @param filter filter== "nameCustomer"
     * @return List of PizzaOrders searching by nameCustomer
     */
    public List<PizzaOrder> findPizzaByName(String filter) {
        return pizzaOrderRepo.findByNameCustomer(filter);
    }

    /**
     * Проверка наличия PizzaInCafe в PizzaOrderRepo.
     * @param id параметр номера PizzaInCafe, которую выбирает Customer в pizzaOrder.mustache.
     * @return - false , если PizzaInCafe нет в Заказе.
     * Если есть - true. Зто означает,что  нельзя сохранить эту PizzaInCafe в PizzaOrderRepo,
     * т.к. уже есть PizzaInCafe в PizzaOrderRepo.
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
    /**
     * Проверка наличия PizzaInCafe в  PizzaRepo
     * @param id параметр номера pizza, которую выбирает ADMIN в pizza.mustache
     * @return false , если PizzaInCafe нет в PizzaRepo.
     * Если есть - true, нельзя сохранить эту pizza в PizzaOrderRepo, т.к. уже заказана
     *  и есть PizzaInCafe в PizzaOrderRepo.
     */
    public boolean isPizzaIdExistInPizza(Long id) {

        return pizzaRepo.existsById(id);
    }

    public void deleteAllOrders() {
        pizzaOrderRepo.deleteAll();
    }

}
