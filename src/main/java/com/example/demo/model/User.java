package com.example.demo.model;

import com.example.demo.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "usr")
@Getter
@Setter


public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String username;
    private String password;
    private boolean active;//активен ли пользователь
    @ElementCollection(targetClass = Role.class,fetch =FetchType.EAGER )//данные из Енам
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;//назначаем атрибуту Set (ролей)


    public User(String username, String password, boolean active, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User() {

    }
}
