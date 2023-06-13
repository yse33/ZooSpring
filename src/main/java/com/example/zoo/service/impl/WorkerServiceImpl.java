package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.CageResource;
import com.example.zoo.controller.resources.WorkerResource;
import com.example.zoo.entity.Worker;
import com.example.zoo.repository.CageRepository;
import com.example.zoo.repository.WorkerRepository;
import com.example.zoo.service.WorkerService;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.example.zoo.mapper.WorkerMapper.WORKER_MAPPER;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final CageRepository cageRepository;

    private final EntityManagerFactory entityManagerFactory;

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
        worker.setCages(cageRepository.findAllById(resource.getCages().stream().map(CageResource::getId).toList()));
        worker.setCreatedDate(resource.getCreatedDate());

        return WORKER_MAPPER.toWorkerResource(workerRepository.save(worker));
    }

    @Override
    public WorkerResource update(WorkerResource resource, long id) {
        Worker toUpdate = workerRepository.getReferenceById(id);
        toUpdate.setName(resource.getName());
        toUpdate.setAge(resource.getAge());
        toUpdate.setSalary(resource.getSalary());
        toUpdate.setCages(cageRepository.findAllById(resource.getCages().stream().map(CageResource::getId).toList()));

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

    @Override
    public List<WorkerResource> findAllAudits(long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(Worker.class, id);
        List<WorkerResource> workerResources = new ArrayList<>();

        for (Number revision : revisions) {
            Worker worker = auditReader.find(Worker.class, id, revision);
            workerResources.add(WORKER_MAPPER.toWorkerResource(worker));
        }

        return workerResources;
    }

    @Override
    public List<Object> findAllAuditsUntilDate(String date) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<?> revisions = auditReader.createQuery()
                .forRevisionsOfEntity(Worker.class, true, true)
                .getResultList();

        List<Object> results = new ArrayList<>();

        for (Object revision : revisions) {
            if(((Worker) revision).getCreatedDate() == null) {
                continue;
            }

            if(((Worker) revision).getCreatedDate().compareTo(Timestamp.valueOf(date)) <= 0) {
                results.add(revision);
            }
        }

        return results;
    }
}
