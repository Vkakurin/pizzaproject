package com.example.demo.service;

import com.example.demo.model.Cafe;
import com.example.demo.model.User;
import com.example.demo.repos.CafeRepo;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.demo.model.enums.Role.USER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CafeServiceTest {
    @Autowired
    private CafeService cafeService;
    @Autowired
    private CafeRepo cafeRepo;
    static private Long cafeIdTest = 60l;//  !!! Перед вводом убедиться с какого Id база SQL будет
    // сохранять новую Тестовую запись,чтобы при запуске CafeServiceTest сначала сохранить ее в методе save ,
    // а затем ее же удалить методом deleteCafeById.
    static private String nameCafe = "Cafe_1";
    private Integer expectSizeCafes = 6;// Смотреть актуальное количество записей в БД перед запуском Общего теста !!!!!



    @Test
    void findByNameCafe() {
        Cafe cafe = new Cafe();
        cafe.setNameCafe(nameCafe);


        List<Cafe> cafeList = cafeService.findByNameCafe(nameCafe);
        List<Cafe> result = cafeList.stream()
                .filter(item -> item.getNameCafe().equals(nameCafe))
                .collect(Collectors.toList());

        Assert.assertFalse(result.isEmpty());

    }


    @Test
    void save() {

        String nameCafe = "Cafe_1";
        String address = "address1";
        String phone = "newPhoneTest";

        Cafe cafe = new Cafe();
        cafe.setCafeId(cafeIdTest);
        cafe.setNameCafe(nameCafe);
        cafe.setAddress(address);
        cafe.setPhone(phone);

        cafeService.save(nameCafe,address,phone);

        Assert.assertEquals(nameCafe, cafe.getNameCafe());
        Assert.assertEquals(address, cafe.getAddress());
        Assert.assertEquals(phone, cafe.getPhone());

    }

    @Test
    void deleteCafeById() {
        Long cafeIdForDelete = Long.valueOf(cafeIdTest);
        cafeService.deleteCafeById(cafeIdForDelete);
        Assert.assertFalse(String.valueOf(cafeIdTest), cafeRepo.existsById(cafeIdForDelete));
    }

    @Test
    void getAllCafes() {
        Assert.assertEquals(Optional.ofNullable(expectSizeCafes), Optional.of(cafeService.getAllCafes().size()));
    }


}