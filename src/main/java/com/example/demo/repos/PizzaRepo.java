package com.example.demo.repos;

import com.example.demo.model.Pizza;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface PizzaRepo extends CrudRepository<Pizza, Long> {
    List<Pizza> findByPizzaName(String pizzaName);

}
