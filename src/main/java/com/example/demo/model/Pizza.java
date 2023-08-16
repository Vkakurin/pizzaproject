package com.example.demo.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please fill the pizzaName")//or "pizzaName cannot be empty"
    @Length(max=255, message = "")

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
