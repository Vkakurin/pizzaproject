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




    public PizzaOrder(String nameCustomer, String addressDelivery, String phoneCustomer, Pizza pizza) {
        this.nameCustomer = nameCustomer;
        this.addressDelivery = addressDelivery;
        this.phoneCustomer = phoneCustomer;
        this.pizza = pizza;



    }
    public String getPizzaId(){
        return pizza != null ? String.valueOf(pizza.getId()) : "<none>";
    }
    public String getPizzaName(){
        return pizza != null ? pizza.getPizzaName() : "<none>";
    }

    public String getNameCafes(){
        return cafe != null ? cafe.getNameCafe() : "<none>";   }


    public PizzaOrder() {

    }
}
