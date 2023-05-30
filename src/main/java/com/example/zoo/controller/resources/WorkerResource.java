package com.example.zoo.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class WorkerResource {
    private Long id;

    private String name;

    private int age;

    private int salary;

    private List<CageResource> cages;
}
