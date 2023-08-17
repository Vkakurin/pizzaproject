package com.example.demo.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@Setter
@Getter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafeId")
    private Cafe cafe;



    public Pizza(String pizzaName, String size, String description, Double price, Cafe cafe) {
        this.pizzaName = pizzaName;
        this.size = size;
        this.description = description;
        this.price = price;
        this.cafe = cafe;
    }
    public String getCafeId(){
        return cafe != null ? String.valueOf(cafe.getCafeId()) : "<none>";
    }



    public Pizza() {

    }



}
