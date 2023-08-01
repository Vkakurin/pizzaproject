package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data

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
