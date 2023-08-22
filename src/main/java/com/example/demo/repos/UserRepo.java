package com.example.demo.repos;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface UserRepo is  Repository to storage Entity "User".
 * I can be used different  methods CRUD extends CrudRepository.
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);// добвляю метод для поиска пользователя по имени

    /***
     * Method returns User by activation code from UserRepo.
     * @param code
     * @return User
     */

    User findByActivationCode(String code);

}
