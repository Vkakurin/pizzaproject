package com.example.demo.model;



import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter


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
