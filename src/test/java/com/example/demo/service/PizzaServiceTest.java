package com.example.demo.service;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.repos.PizzaRepo;
import lombok.var;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PizzaServiceTest {
    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaRepo pizzaRepo;

    private  String pizzaName = "MargorittaTest";
    private Long pizzaIdTest = 115l;//  !!! Перед вводом убедиться с какого Id база SQL будет
    // сохранять новую Тестовую запись,чтобы при запуске PizzaServiceTest сначала сохранить ее в методе save ,
    // а затем ее же удалить методом deletePizzaById.
    private Integer expectSizePizzas = 12;// Смотреть актуальное количество записей в БД перед запуском Общего теста !!!!!



    @Test
    void getAllPizzas() {
        Assert.assertEquals(Optional.ofNullable(expectSizePizzas), Optional.of(pizzaService.getAllPizzas().size()));
    }

    @Test
    void save() {

        String size = "big";
        String description = "description";
        Double price = (double) 5l;


        Pizza pizza = new Pizza();
        String nameCafe = "Cafe_1";
        String address = "addressTest";
        String phone = "newPhone";


        Cafe cafe = new Cafe(nameCafe, address, phone);
        cafe.setCafeId(2l);// cafeId должен быть только существующей Cafe в БД иначе возникает ошибка  SQL Error: 1452, SQLState: 23000

        pizza.setPizzaName(pizzaName);
        pizza.setSize(size);
        pizza.setDescription(description);
        pizza.setPrice(price);
        pizza.setCafe(cafe);

        pizzaService.save(pizzaName, size, description, price, cafe);

        Assert.assertEquals(pizzaName, pizza.getPizzaName());
        Assert.assertEquals(size, pizza.getSize());
        Assert.assertEquals(description, pizza.getDescription());
        Assert.assertEquals(price, pizza.getPrice());
        Assert.assertEquals(nameCafe, cafe.getNameCafe());
        Assert.assertEquals(address, cafe.getAddress());
        Assert.assertEquals(phone, cafe.getPhone());


    }


    @Test
    void deletePizzaById() {
        Long pizzaIdForDelete = Long.valueOf(pizzaIdTest);
        pizzaService.deletePizzaById(pizzaIdForDelete);
        Assert.assertFalse(String.valueOf(pizzaIdTest), pizzaRepo.existsById(pizzaIdForDelete));

    }


    @Test
    void isCafeIdExistInCafe() {
        Long cafeIdTest = 1l;// существующий  cafeId  в bd
        Cafe cafe = new Cafe();
        cafe.setCafeId(cafeIdTest);
        Assert.assertTrue(pizzaService.isCafeIdExistInCafe(cafeIdTest));
    }

}