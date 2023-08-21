package com.example.demo.service;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.repos.CafeRepo;
import com.example.demo.repos.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service CRUD Methods. I can use service for PizzaController via Repository.
 */
@Service
public class PizzaService {
    @Autowired
    private PizzaRepo pizzaRepo;
    @Autowired
    private CafeRepo cafeRepo;

    public PizzaService(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    /**
     * Method delete record Pizza by "id" from PizzaRepo.
     * @param
     */
    public void deletePizzaById(Long id) {
        pizzaRepo.deleteById(id);
    }



    public List<Pizza> findPizzaByName(String filter) {
        return pizzaRepo.findByPizzaName(filter);
    }






    public List<Pizza> getAllPizzas() {
        return StreamSupport.stream(pizzaRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void save(String pizzaName,
                     String size,
                     String description,
                     Double price,
                     Cafe cafe
    ) {
        pizzaRepo.save(new Pizza(pizzaName, size, description, price, cafe));
    }

    public boolean isCafeIdExistInCafe(Long id) {

        return cafeRepo.existsById(id);
    }

}




