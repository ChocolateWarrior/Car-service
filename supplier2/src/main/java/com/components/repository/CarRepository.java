package com.components.repository;

import com.components.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAll();
    List<Car> findALlByNameOrDescriptionContains(String nameContains, String descriptionContains);
}
