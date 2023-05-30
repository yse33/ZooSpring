package com.example.zoo.mapper;

import com.example.zoo.controller.resources.AnimalResource;
import com.example.zoo.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnimalMapper {
    AnimalMapper ANIMAL_MAPPER = Mappers.getMapper(AnimalMapper.class);

    @Mapping(target = "cage.name", source = "animalResource.cage")
    @Mapping(target = "food.name", source = "animalResource.food")
    Animal fromAnimalResource(AnimalResource animalResource);

    @Mapping(target = "cage", source = "animal.cage.name")
    @Mapping(target = "food", source = "animal.food.name")
    AnimalResource toAnimalResource(Animal animal);

    List<AnimalResource> toAnimalResources(List<Animal> animals);
}
