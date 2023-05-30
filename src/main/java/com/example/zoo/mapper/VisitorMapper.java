package com.example.zoo.mapper;

import com.example.zoo.controller.resources.VisitorResource;
import com.example.zoo.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {TicketMapper.class})
public interface VisitorMapper {
    VisitorMapper VISITOR_MAPPER = Mappers.getMapper(VisitorMapper.class);

    Visitor fromVisitorResource(VisitorResource foodResource);

    VisitorResource toVisitorResource(Visitor food);

    List<VisitorResource> toVisitorResources(List<Visitor> foods);
}
