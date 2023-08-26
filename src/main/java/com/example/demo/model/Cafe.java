package com.example.demo.model;



import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * the cafe entity class with private fields in
 * which will be recorded and stored in the database
 */
@Entity
@Setter
@Getter
@Data
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "cafeId cannot be empty")
    private Long cafeId;

    @NotBlank(message = "NameCafe cannot be empty")
    @Length(max=255, message = "NameCafe too long")
    private String nameCafe;

    @NotBlank(message = "Address cannot be empty")
    @Length(max=255, message = "Address too long")
    private String address;

    @NotBlank(message = "Phone cannot be empty")
    @Length(max=255, message = "PhoneNumber too long")
    private String phone;
    /**
     * Constructor with all(without id !) arguments.
     * can be change annotation:"@AllArgsConstructor"
     */
    public Cafe(String nameCafe, String address, String phone) {
        this.nameCafe = nameCafe;
        this.address = address;
        this.phone = phone;
    }
    /**
     * Constructor without arguments.
     * can be change annotation:"@NoArgsConstructor"
     */
    public Cafe() {

    }
}
