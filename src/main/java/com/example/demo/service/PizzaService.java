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
 * Service class CRUD Methods. I can use service for PizzaController via Repository.
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
     *
     * @param
     */
    public void deletePizzaById(Long id) {
        if (pizzaRepo.existsById(id)) {
            pizzaRepo.deleteById(id);
        } else {
            System.out.println("{{{{{{{{{{{PizzaId is missing in BD }}}}}}}}}}}}");
        }
    }


    /***
     * Method to search  pizzaInCafe by his PizzaName into PizzaRepo.
     * @param filter filter == "PizzaName"
     * @return List of Pizza searching by PizzaName.
     */
    public List<Pizza> findPizzaByName(String filter) {
        return pizzaRepo.findByPizzaName(filter);
    }

    /**
     * Method get into the List all records Pizza  into PizzaRepo.
     *
     * @param
     */
    public List<Pizza> getAllPizzas() {
        return StreamSupport.stream(pizzaRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Method put records to new Pizza into PizzaRepo.
     *
     * @param pizzaName
     * @param size
     * @param description
     * @param cafe        cafe
     */
    public void save(String pizzaName,
                     String size,
                     String description,
                     Double price,
                     Cafe cafe
    ) {
        pizzaRepo.save(new Pizza(pizzaName, size, description, price, cafe));
    }

    /***
     * Method finds cafeId into CafeRepo to avoid duplication by linking Pizza and Cafe(PizzaInCafe)
     * @param id
     * @return true if cafeId exist into CafeRepo
     */
    public boolean isCafeIdExistInCafe(Long id) {

        return cafeRepo.existsById(id);
    }


}




