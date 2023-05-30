package com.example.zoo.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class FoodResource {
    private Long id;

    private String name;

    private int amount;

    private List<AnimalResource> animals;
}
