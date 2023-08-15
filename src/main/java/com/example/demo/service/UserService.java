package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepo.findByUsername(username);
    }

    public List<User> getAll(){
        return userRepo.findAll();

    }
    public void deleteById(Long id){
        userRepo.deleteById(id);
    }
    public void save(User user){
        userRepo.save(user);
    }
    public User findByUsername(User user){
       return userRepo.findByUsername(user.getUsername());
    }
}

