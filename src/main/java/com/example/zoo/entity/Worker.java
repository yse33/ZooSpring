package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Worker {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    private int salary;

    @ManyToMany
    private List<Cage> cages;

}
