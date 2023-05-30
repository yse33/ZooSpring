package com.example.zoo.service;

import com.example.zoo.controller.resources.FoodResource;

import java.util.List;

public interface FoodService {
    List<FoodResource> getAll();

    FoodResource getById(long id);

    FoodResource save(FoodResource food);

    FoodResource update(FoodResource food, long id);

    void delete(long id);
}
