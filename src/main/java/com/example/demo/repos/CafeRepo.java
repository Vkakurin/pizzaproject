package com.example.demo.repos;

import com.example.demo.model.Cafe;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Interface CafeRepo is  Repository to storage Entity "Cafe".
 * I can be used different  methods CRUD extends CrudRepository.
 */
public interface CafeRepo extends CrudRepository<Cafe, Long> {

    /***
     * Method List Cafe find by NameCafe from CafeRepo.
     * @param nameCafe
     * @return List Cafe.
     */
    List<Cafe> findByNameCafe(String nameCafe);

}
