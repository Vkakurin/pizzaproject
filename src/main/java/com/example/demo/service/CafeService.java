package com.example.demo.service;


import com.example.demo.model.Cafe;
import com.example.demo.repos.CafeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class CafeService {
    @Autowired
    private CafeRepo cafeRepo;

    public CafeService(CafeRepo cafeRepo) {
        this.cafeRepo = cafeRepo;
    }

    public void deleteCafeById(Long id) {
        cafeRepo.deleteById(id);
    }

    public List<Cafe> getAllCafes() {
        return StreamSupport.stream(cafeRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public List<Cafe> findPizzaByName(String filter) {
        return cafeRepo.findByNameCafe(filter);
    }
    public void findCafeById(String id) {
        cafeRepo.deleteById(Long.valueOf(id));


    }
    public void save( String nameCafe,
                      String address,
                      String phone
    ) {
        cafeRepo.save(new Cafe(nameCafe, address, phone));
    }
}
