package com.example.zoo.repository;

import com.example.zoo.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByCageId(long cageId);

    List<Animal> findAllByFoodId(long foodId);
}
