package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * the PizzaOrder entity class with private fields in which
 * will be recorded and stored in the database
 */
@Entity
@Setter
@Getter
@Data

public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "OrderId cannot be empty")
    private Long order_id;

    @Length(max=255, message = "NameCustomer can't  too long")
    private String nameCustomer;

    @Length(max=255, message = "AddressDelivery can't  too long")
    private String addressDelivery;

    @Length(max=63, message = "PhoneCustomer can't  too long")
    private String phoneCustomer;
    /**
     * Dependency between tables PizzaInCafe and PizzaOrder in the database:
     * there can be many pizzas in one order
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Pizza pizza;

    /**
     * Constructor with all arguments.
     * can be change annotation:"@AllArgsConstructor"
     */
    public PizzaOrder(String nameCustomer, String addressDelivery, String phoneCustomer, Pizza pizza) {
        this.nameCustomer = nameCustomer;
        this.addressDelivery = addressDelivery;
        this.phoneCustomer = phoneCustomer;
        this.pizza = pizza;
    }

    /**
     * method  to use parameter "id as PizzaId" in pizzaOrder.mustache.
     * @return "id as PizzaId" or "<none>" if id == null.
     * I use this method to debug the frontend
     */
    public String getPizzaId() {
        return pizza != null ? String.valueOf(pizza.getId()) : "<none>";
    }

    /**
     * method  to use parameter "PizzaName" in pizzaOrder.mustache.
     * @return pizzaName or "<none>" if PizzaName == null.
     * I use this method to debug the frontend
     */
    public String getPizzaName() {
        return pizza != null ? pizza.getPizzaName() : "<none>";
    }

    /**
     * Constructor without arguments.
     * can be change annotation:"@NoArgsConstructor"
     */
    public PizzaOrder() {

    }


}
