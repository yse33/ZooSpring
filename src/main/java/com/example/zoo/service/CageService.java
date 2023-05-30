package com.example.zoo.service;

import com.example.zoo.controller.resources.CageResource;

import java.util.List;

public interface CageService {
    List<CageResource> getAll();

    CageResource getById(long id);

    CageResource save(CageResource cage);

    CageResource update(CageResource cage, long id);

    void delete(long id);
}
