package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.WorkerResource;
import com.example.zoo.entity.Worker;
import com.example.zoo.repository.CageRepository;
import com.example.zoo.repository.WorkerRepository;
import com.example.zoo.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.zoo.mapper.WorkerMapper.WORKER_MAPPER;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final CageRepository cageRepository;

    @Override
    public List<WorkerResource> getAll() {
        return WORKER_MAPPER.toWorkerResources(workerRepository.findAll());
    }

    @Override
    public WorkerResource getById(long id) {
        return WORKER_MAPPER.toWorkerResource(workerRepository.getReferenceById(id));
    }

    @Override
    public WorkerResource save(WorkerResource resource) {
        Worker worker = WORKER_MAPPER.fromWorkerResource(resource);
        worker.setCages(null);

        return WORKER_MAPPER.toWorkerResource(workerRepository.save(worker));
    }

    @Override
    public WorkerResource update(WorkerResource resource, long id) {
        Worker toUpdate = workerRepository.getReferenceById(id);
        toUpdate.setName(resource.getName());
        toUpdate.setAge(resource.getAge());
        toUpdate.setSalary(resource.getSalary());

        return WORKER_MAPPER.toWorkerResource(workerRepository.save(toUpdate));
    }

    @Override
    public void delete(long id) {
        cageRepository.findAllByWorkers_Id(id).forEach(this::removeCage);
        workerRepository.deleteById(id);
    }

    public void removeCage(Worker worker) {
        worker.setCages(null);
        workerRepository.save(worker);
    }
}
