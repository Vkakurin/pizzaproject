package com.example.demo.repos;

import com.example.demo.model.Pizza;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;
/**
 * Interface PizzaRepo is  Repository to storage Entity "Pizza".
 * I can be used different  methods CRUD extends CrudRepository.
 */
public interface PizzaRepo extends CrudRepository<Pizza, Long> {

    /***
     * Method List Pizza by PizzaName from PizzaRepo.
     * @param pizzaName
     * @return List Pizza.
     */
    List<Pizza> findByPizzaName(String pizzaName);

}
