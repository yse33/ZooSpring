package com.example.zoo.service.impl;

import com.example.zoo.controller.resources.FoodResource;
import com.example.zoo.entity.Animal;
import com.example.zoo.entity.Food;
import com.example.zoo.repository.AnimalRepository;
import com.example.zoo.repository.FoodRepository;
import com.example.zoo.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.zoo.mapper.FoodMapper.FOOD_MAPPER;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final AnimalRepository animalRepository;

    @Override
    public List<FoodResource> getAll() {
        return FOOD_MAPPER.toFoodResources(foodRepository.findAll());
    }

    @Override
    public FoodResource getById(long id) {
        return FOOD_MAPPER.toFoodResource(foodRepository.getReferenceById(id));
    }

    @Override
    public FoodResource save(FoodResource resource) {
        Food food = FOOD_MAPPER.fromFoodResource(resource);
        food.setAnimals(null);

        return FOOD_MAPPER.toFoodResource(foodRepository.save(food));
    }

    @Override
    public FoodResource update(FoodResource resource, long id) {
        Food toUpdate = foodRepository.getReferenceById(id);
        toUpdate.setName(resource.getName());
        toUpdate.setAmount(resource.getAmount());

        return FOOD_MAPPER.toFoodResource(foodRepository.save(toUpdate));
    }

    @Override
    public void delete(long id) {
        animalRepository.findAllByFoodId(id).forEach(this::removeFood);
        foodRepository.deleteById(id);
    }

    public void removeFood(Animal animal) {
        animal.setFood(null);
        animalRepository.save(animal);
    }
}
