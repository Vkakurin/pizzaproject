package com.example.demo.repos;

import com.example.demo.domain.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PizzaRepo extends CrudRepository<Pizza, Long> {
    List<Pizza> findByPizzaName(String pizzaName);
}
