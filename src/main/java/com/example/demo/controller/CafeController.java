package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.repos.CafeRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CafeController {
    private final CafeRepo cafeRepo;
    public CafeController(CafeRepo cafeRepo) {
        this.cafeRepo = cafeRepo;
    }

    @GetMapping("/cafe")
    public String cafe(
            @RequestParam(required = false, defaultValue = "") String filterCafe,
            Model model) {
        Iterable<Cafe> cafes = cafeRepo.findAll();
        if (filterCafe != null && !filterCafe.isEmpty()) {
            cafes = cafeRepo.findByNameCafe(filterCafe);
        } else {
            cafes = cafeRepo.findAll();
        }
        model.addAttribute("cafes", cafes);
        model.addAttribute("filterCafe",filterCafe);
        return "cafe";
    }

    @PostMapping("/cafe")
    public String add(
            @RequestParam String nameCafe,
            @RequestParam String address,
            @RequestParam String phone,
            Model model) {
        Cafe cafe = new Cafe(nameCafe, address, phone);
        cafeRepo.save(cafe);
        Iterable<Cafe> cafes = cafeRepo.findAll();
        model.addAttribute("cafes", cafes);
        return "cafe";
    }

}
