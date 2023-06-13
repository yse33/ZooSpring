package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.AnimalResource;
import com.example.zoo.entity.Animal;
import com.example.zoo.repository.AnimalRepository;
import com.example.zoo.repository.CageRepository;
import com.example.zoo.repository.FoodRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;
import com.example.zoo.service.AnimalService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.zoo.mapper.AnimalMapper.ANIMAL_MAPPER;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CageRepository cageRepository;
    private final FoodRepository foodRepository;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<AnimalResource> getAll() {
        return ANIMAL_MAPPER.toAnimalResources(animalRepository.findAll());
    }

    @Override
    public AnimalResource getById(long id) {
        return ANIMAL_MAPPER.toAnimalResource(animalRepository.getReferenceById(id));
    }

    @Override
    public AnimalResource save(AnimalResource resource) {
        Animal animal = ANIMAL_MAPPER.fromAnimalResource(resource);
        cageRepository.getCageByName(resource.getCage()).ifPresentOrElse(
                animal::setCage,
                () -> {
                    throw new EntityNotFoundException("");
                }
        );
        foodRepository.getFoodByName(resource.getFood()).ifPresentOrElse(
                animal::setFood,
                () -> {
                    throw new EntityNotFoundException("");
                }
        );

        return ANIMAL_MAPPER.toAnimalResource(animalRepository.save(animal));
    }

    @Override
    public AnimalResource update(AnimalResource resource, long id) {
        Animal toUpdate = animalRepository.getReferenceById(id);
        toUpdate.setName(resource.getName());
        toUpdate.setAge(resource.getAge());
        toUpdate.setSpecies(resource.getSpecies());
        toUpdate.setGender(resource.getGender());

        return ANIMAL_MAPPER.toAnimalResource(animalRepository.save(toUpdate));
    }

    @Override
    public void delete(long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public List<AnimalResource> findAllAudits(long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(Animal.class, id);
        List<AnimalResource> results = new ArrayList<>();

        for (Number revision : revisions) {
            Animal animal = auditReader.find(Animal.class, id, revision);
            if (animal.getValidFrom() == null || animal.getValidFrom().compareTo(new Date()) <= 0) {
                results.add(ANIMAL_MAPPER.toAnimalResource(animal));
            }
        }

        return results;
    }

    @Override
    public List<AnimalResource> findAllAnimalsBySpecies(String species) {
        return ANIMAL_MAPPER.toAnimalResources(animalRepository.findAllBySpecies(species));
    }

    @Override
    public List<AnimalResource> findAllAnimalsByFood(String food) {
        return ANIMAL_MAPPER.toAnimalResources(animalRepository.findAllByFoodId(foodRepository.getFoodByName(food).get().getId()));
    }

    @Override
    public List<AnimalResource> findAllAnimalsByCage(String cage) {
        return ANIMAL_MAPPER.toAnimalResources(animalRepository.findAllByCageId(cageRepository.getCageByName(cage).get().getId()));
    }

}
