package com.example.demo.repos;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);// добвляю метод для поиска пользователя по имени




}
