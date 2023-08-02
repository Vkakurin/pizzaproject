package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.repos.CafeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CafeController {
    @Autowired
    private  CafeRepo cafeRepo;

    @GetMapping("/cafe")
    public String getCafe(
            @RequestParam(required = false, defaultValue = "") String filter,
            Map<String, Object> model) {
        Iterable<Cafe> cafes;
        if (filter != null && !filter.isEmpty()) {
            cafes = cafeRepo.findByNameCafe(filter);
        } else {
            cafes = cafeRepo.findAll();
        }
        model.put("cafes", cafes);
        model.put("filter",filter);
        return "cafe";
    }

    @PostMapping("/cafe")
    public String addCafe(
            @RequestParam String nameCafe,
            @RequestParam String address,
            @RequestParam String phone,
            Map<String, Object> model) {
        Cafe cafe = new Cafe(nameCafe, address, phone);
        cafeRepo.save(cafe);
        Iterable<Cafe> cafes = cafeRepo.findAll();
        model.put("cafes", cafes);
        return "cafe";
    }

}
