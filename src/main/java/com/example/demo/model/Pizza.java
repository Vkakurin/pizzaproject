package com.example.demo.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * the Pizza entity class with private fields in which
 * will be recorded and stored in the database
 */

@Entity
@Data
@Setter
@Getter
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "PizzaId cannot be empty")
    private Long id;

    @NotBlank(message = "Please fill the pizzaName")//or "pizzaName cannot be empty"
    @Length(max=63, message = "PizzaName too long")
    private String pizzaName;

    @Length(max=45, message = "Size of pizza can't  too long")
    @NotBlank(message = "Please fill the size")
    private String size;

    @Length(max=2048, message = "Description of pizza can't  too long")
    @NotBlank(message = "Please fill the pizzaName. Maximum 2Kb")
    private String description;

    private Double price;
    /**
     * Dependency between tables CAFE and PIZZA in the database:
     * there can be many pizzas in one cafe
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafeId")
    private Cafe cafe;

    /**
     * Constructor with all arguments.
     * can be change annotation:"@AllArgsConstructor"
     */

    public Pizza(String pizzaName, String size, String description, Double price, Cafe cafe) {
        this.pizzaName = pizzaName;
        this.size = size;
        this.description = description;
        this.price = price;
        this.cafe = cafe;
    }

    /**
     * Constructor without arguments.
     * can be change annotation:"@NoArgsConstructor"
     */
    public Pizza() {

    }

    /**
     * method  to use parameter "nameCafe" in pizza.mustache.
     * @return nameCafe or "<none>" if nameCafe == null.
     * I use this method to debug the frontend
     */
    public String getNameCafe(){
        return cafe != null ? cafe.getNameCafe() : "<none>";   }

    /**
     * method  to use parameter "cafeId" in pizza.mustache.
     * @return cafeId or "<none>" if cafeId == null.
     * I use this method to debug the frontend
     */
    public String getCafeId(){
        return cafe != null ? String.valueOf(cafe.getCafeId()) : "<none>";
    }
}
