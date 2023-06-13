package com.example.zoo.controller.resources;

import lombok.Data;

import java.util.Date;

@Data
public class AnimalResource {
    private Long id;

    private String name;

    private int age;

    private String species;

    private String gender;

    private String cage;

    private String food;

    private Date createdDate;

    private Date validFrom;
}
