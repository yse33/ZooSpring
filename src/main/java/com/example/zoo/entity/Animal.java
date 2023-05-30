package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Animal {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    private String species;

    private String gender;

    @ManyToOne
    private Cage cage;

    @ManyToOne
    private Food food;
}
