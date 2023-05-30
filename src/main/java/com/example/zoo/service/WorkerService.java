package com.example.zoo.service;

import com.example.zoo.controller.resources.WorkerResource;

import java.util.List;

public interface WorkerService {
    List<WorkerResource> getAll();

    WorkerResource getById(long id);

    WorkerResource save(WorkerResource worker);

    WorkerResource update(WorkerResource worker, long id);

    void delete(long id);
}
