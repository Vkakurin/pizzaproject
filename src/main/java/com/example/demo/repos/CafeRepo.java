package com.example.demo.repos;

import com.example.demo.domain.Cafe;
import org.springframework.data.repository.CrudRepository;

public interface CafeRepo extends CrudRepository<Cafe, Long> {

}
