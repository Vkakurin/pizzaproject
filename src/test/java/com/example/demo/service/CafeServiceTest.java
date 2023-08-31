package com.example.demo.service;

import com.example.demo.model.Cafe;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CafeServiceTest {
    @Autowired
    private CafeService cafeService;
    static private Long cafeIdTest = 100L;
    static private String nameCafe = "Cafe_1";

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

    }

    @Test
    void deleteCafeById() {
    }

    @Test
    void getAllCafes() {
    }


}