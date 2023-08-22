package com.example.demo.repos;

import com.example.demo.model.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * Interface PizzaOrderRepo is  Repository to storage Entity "PizzaOrder".
 * I can be used different  methods CRUD extends CrudRepository.
 */
public interface PizzaOrderRepo extends CrudRepository<PizzaOrder, Long> {

    /***
     * Method List PizzaOrder find by NameCustomer into PizzaOrderRepo.
     * @param nameCustomer
     * @return List PizzaOrder.
     */
    List<PizzaOrder> findByNameCustomer(String nameCustomer);



}
