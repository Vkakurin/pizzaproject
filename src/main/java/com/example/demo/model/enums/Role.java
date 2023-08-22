package com.example.demo.model.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum Roles. I use Roles to get access to methods of Controllers.
 * Implements GrantedAuthority to use authority by Role.
 */

public enum Role implements GrantedAuthority {// Указываем состояние роли
    USER,
    CUSTOMER,
    ADMIN ;// у админф больше прав чем пользователь
    @Override
    public String getAuthority(){
        return name();
    }// переводит состояния в строки-возвращаем в строчные представления пользователя.
}
