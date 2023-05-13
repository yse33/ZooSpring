package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cage {
    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private int capacity;

    @OneToMany(mappedBy = "cage")
    private List<Animal> animals;

    @ManyToMany
    private List<Worker> workers;
}