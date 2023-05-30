package com.example.zoo.entity;

import com.fasterxml.jackson.annotation.JacksonInject;
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
    @JoinTable(
            name = "worker_cage",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "cage_id")
    )
    private List<Cage> cages;
}
