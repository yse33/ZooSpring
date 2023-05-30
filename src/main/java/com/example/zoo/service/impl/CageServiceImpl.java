package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.CageResource;
import com.example.zoo.entity.Animal;
import com.example.zoo.entity.Cage;
import com.example.zoo.entity.Worker;
import com.example.zoo.repository.AnimalRepository;
import com.example.zoo.repository.CageRepository;
import com.example.zoo.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.zoo.service.CageService;

import java.util.List;

import static com.example.zoo.mapper.CageMapper.CAGE_MAPPER;

@Service
@RequiredArgsConstructor
public class CageServiceImpl implements CageService {
    private final CageRepository cageRepository;
    private final AnimalRepository animalRepository;
    private final WorkerRepository workerRepository;

    @Override
    public List<CageResource> getAll() {
        return CAGE_MAPPER.toCageResources(cageRepository.findAll());
    }

    @Override
    public CageResource getById(long id) {
        return CAGE_MAPPER.toCageResource(cageRepository.getReferenceById(id));
    }

    @Override
    public CageResource save(CageResource resource) {
        Cage cage = CAGE_MAPPER.fromCageResource(resource);
        cage.setAnimals(null);
        cage.setWorkers(null);

        return CAGE_MAPPER.toCageResource(cageRepository.save(cage));
    }

    @Override
    public CageResource update(CageResource resource, long id) {
        Cage toUpdate = cageRepository.getReferenceById(id);
        toUpdate.setName(resource.getName());
        toUpdate.setType(resource.getType());
        toUpdate.setCapacity(resource.getCapacity());

        return CAGE_MAPPER.toCageResource(cageRepository.save(toUpdate));
    }

    @Override
    public void delete(long id) {
        animalRepository.findAllByCageId(id).forEach(this::removeCageFromAnimal);
        workerRepository.findAllByCages_Id(id).forEach(worker -> removeCageFromWorker(worker, id));
        cageRepository.deleteById(id);
    }

    private void removeCageFromWorker(Worker worker, long cageId) {
        worker.getCages().removeIf(cage -> cage.getId().equals(cageId));
        workerRepository.save(worker);
    }

    private void removeCageFromAnimal(Animal animal) {
        animal.setCage(null);
        animalRepository.save(animal);
    }

}
