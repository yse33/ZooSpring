package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Food {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int amount;

    @OneToMany(mappedBy = "food")
    private List<Animal> animals;
}
