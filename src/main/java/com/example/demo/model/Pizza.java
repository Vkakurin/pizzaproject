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
    private Double price;

    public Pizza(String pizzaName, String size, String description, Double price) {
        this.pizzaName = pizzaName;
        this.size = size;
        this.description = description;
        this.price = price;
    }

    public Pizza() {

    }
}
