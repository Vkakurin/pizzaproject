package com.example.demo.repos;

import com.example.demo.model.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PizzaOrderRepo extends CrudRepository<PizzaOrder, Long> {


    List<PizzaOrder> findByNameCustomer(String nameCustomer);



}
