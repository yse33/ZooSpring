package com.example.zoo.service;

import com.example.zoo.controller.resources.VisitorResource;

import java.util.List;

public interface VisitorService {
    List<VisitorResource> getAll();

    VisitorResource getById(long id);

    VisitorResource save(VisitorResource visitor);

    VisitorResource update(VisitorResource visitor, long id);

    void delete(long id);

    List<VisitorResource> findAllAudits(long id);

    List<VisitorResource> findAllVisitorsByYear(int year);
}
