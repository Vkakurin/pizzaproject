package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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


    public PizzaOrder(String nameCustomer, String addressDelivery, String phoneCustomer) {
        this.nameCustomer = nameCustomer;
        this.addressDelivery = addressDelivery;
        this.phoneCustomer = phoneCustomer;

    }

    public PizzaOrder() {
    }
}
