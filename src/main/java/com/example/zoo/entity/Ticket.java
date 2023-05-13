package com.example.zoo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private int price;

    @ManyToOne
    private Visitor visitor;

}
