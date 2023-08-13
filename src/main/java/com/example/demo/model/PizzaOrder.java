package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Data

public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;
    private String nameCustomer;
    private String addressDelivery;
    private String phoneCustomer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Pizza pizza;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cafeId")
    private Cafe cafe;

    public PizzaOrder(String nameCustomer, String addressDelivery, String phoneCustomer, Pizza pizza,Cafe cafe) {
        this.nameCustomer = nameCustomer;
        this.addressDelivery = addressDelivery;
        this.phoneCustomer = phoneCustomer;
        this.pizza = pizza;
        this.cafe= cafe;

    }



    public PizzaOrder() {
    }
}
