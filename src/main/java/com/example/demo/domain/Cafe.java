package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cafeId;
    private String nameCafe;
    private String address;
    private String phone;

}
