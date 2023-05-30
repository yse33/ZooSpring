package com.example.zoo.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class CageResource {
    private Long id;

    private String name;

    private String type;

    private int capacity;

    private List<AnimalResource> animals;

    private List<WorkerResource> workers;
}
