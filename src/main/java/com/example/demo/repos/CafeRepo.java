package com.example.demo.repos;

import com.example.demo.model.Cafe;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Interface CafeRepo is name Repository to storage Entity "Cafe".
 * I can be used different  methods CRUD extends CrudRepository.
 */
public interface CafeRepo extends CrudRepository<Cafe, Long> {
    List<Cafe> findByNameCafe(String nameCafe);

}
