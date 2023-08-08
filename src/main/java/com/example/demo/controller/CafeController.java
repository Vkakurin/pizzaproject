package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.repos.CafeRepo;
import com.example.demo.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CafeController {
    @Autowired
    private  CafeRepo cafeRepo;
    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/cafe")
    public String getCafeByName(
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
            Model model) {
        Cafe cafe = new Cafe(nameCafe, address, phone);
        cafeRepo.save(cafe);
        Iterable<Cafe> cafes = cafeRepo.findAll();
        model.addAttribute("cafes", cafes);
        return "redirect:/cafe";
    }
    @GetMapping("/deleteCafe/{id}")
    public String deleteCafeById(
            Model model,
            @PathVariable Long id){
        cafeService.deleteCafeById(id);
        model.addAttribute("cafe", cafeService.getAllCafes());
        return "redirect:/cafe";
    }
}
