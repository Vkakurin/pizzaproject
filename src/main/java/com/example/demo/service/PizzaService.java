package com.example.demo.service;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.repos.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepo pizzaRepo;

    public PizzaService(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    public void deletePizzaById(Long id) {
        pizzaRepo.deleteById(id);
    }
    public void findPizzaById(Long id) {
        pizzaRepo.findById(id);
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
    public boolean existsPizzaById(Long id) {

        return pizzaRepo.existsById(id);
    }
}



