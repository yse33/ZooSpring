package com.example.zoo.repository;

import com.example.zoo.entity.Cage;
import com.example.zoo.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CageRepository extends JpaRepository<Cage, Long> {

    Optional<Cage> getCageByName(String name);

    List<Worker> findAllByWorkers_Id(long id);
}
