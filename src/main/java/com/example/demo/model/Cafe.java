package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data

public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cafeId;
    private String nameCafe;
    private String address;
    private String phone;

    public Cafe(String nameCafe, String address, String phone) {
        this.nameCafe = nameCafe;
        this.address = address;
        this.phone = phone;
    }

    public Cafe() {

    }
}
