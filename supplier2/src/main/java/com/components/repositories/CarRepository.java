package com.components.repositories;

import com.components.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAll();
    List<Car> findAllByBrandOrModelContains(String nameContains, String descriptionContains);
}
