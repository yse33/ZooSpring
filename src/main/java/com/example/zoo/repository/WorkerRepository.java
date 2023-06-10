package com.example.zoo.repository;

import com.example.zoo.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findAllByCages_Id(long cageId);
}
