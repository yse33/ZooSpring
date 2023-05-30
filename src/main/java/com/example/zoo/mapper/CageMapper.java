package com.example.zoo.mapper;

import com.example.zoo.controller.resources.CageResource;
import com.example.zoo.entity.Cage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AnimalMapper.class, FoodMapper.class})
public interface CageMapper {
    CageMapper CAGE_MAPPER = Mappers.getMapper(CageMapper.class);

    Cage fromCageResource(CageResource cageResource);

    CageResource toCageResource(Cage cage);

    List<CageResource> toCageResources(List<Cage> cages);
}
