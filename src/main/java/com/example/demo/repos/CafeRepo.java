package com.example.demo.repos;

import com.example.demo.model.Cafe;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CafeRepo extends CrudRepository<Cafe, Long> {
    List<Cafe> findByNameCafe(String nameCafe);

}
