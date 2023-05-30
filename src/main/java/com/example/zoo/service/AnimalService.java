package com.example.zoo.service;

import com.example.zoo.controller.resources.AnimalResource;

import java.util.List;

public interface AnimalService {
    List<AnimalResource> getAll();

    AnimalResource getById(long id);

    AnimalResource save(AnimalResource animal);

    AnimalResource update(AnimalResource animal, long id);

    void delete(long id);
}
