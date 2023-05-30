package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.AnimalResource;
import com.example.zoo.entity.Animal;
import com.example.zoo.entity.Cage;
import com.example.zoo.entity.Food;
import com.example.zoo.repository.AnimalRepository;
import com.example.zoo.repository.CageRepository;
import com.example.zoo.repository.FoodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.zoo.service.AnimalService;

import java.util.List;

import static com.example.zoo.mapper.AnimalMapper.ANIMAL_MAPPER;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CageRepository cageRepository;
    private final FoodRepository foodRepository;

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
        Animal animal = animalRepository.getReferenceById(id);

        Cage cage = animal.getCage();
        cage.getAnimals().remove(animal);
        cageRepository.save(cage);

        Food food = animal.getFood();
        food.getAnimals().remove(animal);
        foodRepository.save(food);

        animalRepository.deleteById(id);
    }

}
