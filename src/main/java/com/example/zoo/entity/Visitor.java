package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Visitor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @OneToMany(mappedBy = "visitor")
    private List<Ticket> tickets;

}
