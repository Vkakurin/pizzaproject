package com.example.demo.service;

import com.example.demo.model.Pizza;
import com.example.demo.repos.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PizzaService {
    @Autowired
    private final PizzaRepo pizzaRepo;

    public PizzaService(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    public void deletePizzaById(Long id) {
        pizzaRepo.deleteById(id);
    }

    public List<Pizza> getAllPizzas() {
        return StreamSupport.stream(pizzaRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public Object findPizzaById(Long id) {
        Optional<Optional<Pizza>> pizzas;
        if (id != null) {
            return pizzas = Optional.of(Optional.of(pizzaRepo.findById(id).get()));
        } else {
            return pizzas = pizzaRepo.findAllById((Long) null);
        }

    }


}
