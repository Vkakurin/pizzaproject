package com.example.demo.service;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
import com.example.demo.model.PizzaOrder;
import com.example.demo.repos.PizzaOrderRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PizzaOrderServiceTest {
    @Autowired
    private PizzaOrderService pizzaOrderService;
    @Autowired
    private PizzaOrderRepo pizzaOrderRepo;
    private Long pizzaOrderIdTest = 105l;//  !!! Перед вводом убедиться с какого Id база SQL будет
    // сохранять новую Тестовую запись,чтобы при запуске PizzaOrderServiceTest сначала сохранить   ее в методе save ,
    // а затем ее же удалить методом deleteOrderById.
    private Integer expectSizePizzas = 7;// Смотреть актуальное количество записей в БД перед запуском Общего теста !!!!!



    @Test
    void getAllOrders() {
        Assert.assertEquals(Optional.ofNullable(expectSizePizzas), Optional.of(pizzaOrderService.getAllOrders().size()));
    }

    @Test
    void save() {
        Cafe cafe = new Cafe();
        cafe.setCafeId(200l);
        String nameCafe = "Cafe_1";
        String address = "addressForTest";
        String phone = "newPhone";

        cafe.setNameCafe(nameCafe);
        cafe.setAddress(address);
        cafe.setPhone(phone);

        String pizzaName = "BestPizzaTest";
        String size = "big";
        String description = "description";
        Double price = (double) 5l;

        Pizza pizza = new Pizza();
        pizza.setId(7l);// pizzaId должен быть только существующей Pizza в БД иначе возникает ошибка  SQL:
        //https://stackoverflow.com/questions/32505343/sql-error-1452-sqlstate-23000-cannot-add-or-update-a-child-row-a-foreign-k

        pizza.setPizzaName(pizzaName);
        pizza.setSize(size);
        pizza.setDescription(description);
        pizza.setPrice(price);
        pizza.setCafe(cafe);

        PizzaOrder pizzaOrder = new PizzaOrder();

        String nameCustomer = "IvanovTest";
        String addressDelivery = "customerAddress";
        String customerPhone = "customerPhone";


        pizzaOrder.setOrder_id(13l);
        pizzaOrder.setNameCustomer(nameCustomer);
        pizzaOrder.setAddressDelivery(addressDelivery);
        pizzaOrder.setPhoneCustomer(customerPhone);
        pizzaOrder.setPizza(pizza);
        System.out.println("+++++++++++++++" + pizza);
        pizzaOrderService.save(nameCustomer, addressDelivery, customerPhone, pizza);
        System.out.println("////////////////" + pizzaOrder);
        Assert.assertEquals(pizzaName, pizza.getPizzaName());
        Assert.assertEquals(size, pizza.getSize());
        Assert.assertEquals(description, pizza.getDescription());
        Assert.assertEquals(price, pizza.getPrice());
        Assert.assertEquals(nameCustomer, pizzaOrder.getNameCustomer());
        Assert.assertEquals(addressDelivery, pizzaOrder.getAddressDelivery());
        Assert.assertEquals(customerPhone, pizzaOrder.getPhoneCustomer());



    }
    @Test
    void deleteOrderById() {
        Long pizzaOrderIdForDelete = Long.valueOf(pizzaOrderIdTest);
        pizzaOrderService.deleteOrderById(pizzaOrderIdForDelete);
        Assert.assertFalse(String.valueOf(pizzaOrderIdTest), pizzaOrderRepo.existsById(pizzaOrderIdForDelete));
    }


    @Test
    void isPizzaIdExistInOrders() {
        Assert.assertTrue(pizzaOrderService.isPizzaIdExistInOrders(7l));
        Assert.assertFalse(pizzaOrderService.isPizzaIdExistInOrders(1l));
    }

    @Test
    void isPizzaIdExistInPizza() {
        Assert.assertTrue(pizzaOrderService.isPizzaIdExistInPizza(7l));
        Assert.assertFalse(pizzaOrderService.isPizzaIdExistInOrders(1l));
    }

}