package com.example.zoo.mapper;

import com.example.zoo.controller.resources.WorkerResource;
import com.example.zoo.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CageMapper.class})
public interface WorkerMapper {
    WorkerMapper WORKER_MAPPER = Mappers.getMapper(WorkerMapper.class);

    Worker fromWorkerResource(WorkerResource foodResource);

    WorkerResource toWorkerResource(Worker food);

    List<WorkerResource> toWorkerResources(List<Worker> foods);
}
