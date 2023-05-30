package com.example.zoo.mapper;

import com.example.zoo.controller.resources.FoodResource;
import com.example.zoo.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AnimalMapper.class})
public interface FoodMapper {
    FoodMapper FOOD_MAPPER = Mappers.getMapper(FoodMapper.class);

    Food fromFoodResource(FoodResource foodResource);

    FoodResource toFoodResource(Food food);

    List<FoodResource> toFoodResources(List<Food> foods);
}
