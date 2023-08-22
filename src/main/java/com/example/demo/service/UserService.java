package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * Service class of the CRUD Methods. I can use service for UserController via Repository.
 */

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;


    /***
     * Overriding UserDetails interface  authorizes the user with username and password.
     * @param username
     * @return (UserDetails) username
     * @throws UsernameNotFoundException when username not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByUsername(username);
    }

    /**
     * Method get into the List all records of User  into UserRepo.
     * @param
     */
    public List<User> getAll() {return userRepo.findAll();}

    /**
     * Method delete record User by "userId" from UserRepo.
     *
     * @param
     */
    public void deleteById(Long id) { userRepo.deleteById(id);}

    public boolean isUserIdExist(String id) {
        return userRepo.existsById(Long.valueOf(id));
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public User findByUsername(User user) {
        return userRepo.findByUsername(user.getUsername());
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));// назначаем роль
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello? %s! \n" +
                            "Welcome to Pizza. Please visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }


    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}

