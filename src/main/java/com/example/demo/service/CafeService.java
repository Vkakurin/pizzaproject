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
 * Service class uses Service CRUD methods to work with Repository.
 *
 * I can use service for CafeController via Repository.
 */
@Service
public class CafeService {
    @Autowired
    private CafeRepo cafeRepo;

    public CafeService(CafeRepo cafeRepo ) {
        this.cafeRepo = cafeRepo;

    }

    /**
     * Method delete records Cafe by "CafeId" from CafeRepo.
     * @param
     */
    public void deleteCafeById(Long id) {
        if(cafeRepo.existsById(id)){
        cafeRepo.deleteById(id);
        }else {
            System.out.println("{{{{{{{{{{{{{{CafeId is missing in the CafeRepo}}}}}}}}}}}}");
        }
    }

    /**
     * Method get into the List all records Cafe  from CafeRepo.
     * @param
     */
    public List<Cafe> getAllCafes() {
        return StreamSupport.stream(cafeRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Method find records Cafe by "nameCafe" from CafeRepo.
     * @param
     */
    public List<Cafe> findByNameCafe(String filter) {
        return cafeRepo.findByNameCafe(filter);
    }

    /**
     * Method put records new Cafe by "CafeId" into CafeRepo.
     * @param
     */
    public void save( String nameCafe,
                      String address,
                      String phone
    ) {
        cafeRepo.save(new Cafe(nameCafe, address, phone));
    }
}
