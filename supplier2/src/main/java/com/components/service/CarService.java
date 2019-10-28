package com.components.service;

import com.components.entity.Car;
import com.components.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllProducts() {
        return carRepository.findAll();
    }

    public List<Car> getAllProductsByQuery(String query) {
        return carRepository.findAllByBrandOrModelContains(query, query);
    }
}