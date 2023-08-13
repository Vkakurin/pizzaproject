package com.example.demo.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {// Указываем состояние роли
    USER,
    ADMIN ;// у админф больше прав чем пользователь
    @Override
    public String getAuthority(){
        return name();
    }// переводит состояния в строки-возвращаем в строчные представления пользователя.
}
