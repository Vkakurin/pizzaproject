package com.example.demo.controller;

import com.example.demo.model.Cafe;
import com.example.demo.model.Pizza;
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

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }
    Iterable<Cafe> cafes;


    @GetMapping("/cafe")
    public String getCafeByName(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {

        if (filter != null && !filter.isEmpty()) {
            cafes = cafeService.findPizzaByName(filter);
        } else {
            cafes = cafeService.getAllCafes();
        }
        model.addAttribute("cafes", cafes);
        model.addAttribute("filter",filter);
        return "cafe";
    }

    @PostMapping("/cafe")
    public String addCafe(
            @RequestParam String nameCafe,
            @RequestParam String address,
            @RequestParam String phone,
            Model model) {

        cafeService.save(nameCafe, address, phone);
        Iterable<Cafe> cafes = cafeService.getAllCafes();
        model.addAttribute("cafes", cafes);
        return "redirect:/cafe";



    }
    @GetMapping("/deleteCafe{cafeId}")
    public String deleteCafeById(
            Model model,
            @RequestParam(required = false, defaultValue = "") String cafeId){
        cafeService.deleteCafeById(Long.valueOf(cafeId));
        model.addAttribute("cafes", cafeService.getAllCafes());
        return "redirect:/cafe";
    }
    //todo
    @GetMapping("/findCafeById{cafeId}")
    public String findCafeById(
            Model model,
            @RequestParam(required = false, defaultValue = "") String cafeId){
        if (cafeId != null && !cafeId.isEmpty()) {
            cafeService.findCafeById(cafeId);
        } else {
            cafes = cafeService.getAllCafes();
        }

        model.addAttribute("cafes", cafes);
        return "redirect:/cafe";
    }
}
