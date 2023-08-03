package com.example.demo.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pizzaName;
    private String size;
    private String description;

    public Pizza(String pizzaName, String size, String description) {

        this.pizzaName = pizzaName;
        this.size = size;
        this.description = description;
    }

    public Pizza() {

    }
}
