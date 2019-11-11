package com.components.services;

import com.components.entities.Car;
import com.components.generators.DatabaseRandomGenerator;
import com.components.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;
    private DatabaseRandomGenerator databaseRandomGenerator;

    @Autowired
    public CarService(CarRepository carRepository, DatabaseRandomGenerator databaseRandomGenerator) {
        this.carRepository = carRepository;
        this.databaseRandomGenerator = databaseRandomGenerator;
    }

    public Map<Long, BigDecimal> getIdAndPrice() {
        return carRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Car::getId, Car::getPrice));
    }

    public Car getById(long id) {
        return carRepository.findById(id).orElseThrow();
    }

    private List<Car> findAll() {
        return carRepository.findAll();
    }

    @PostConstruct
    private void generateDB() {
        if (findAll().isEmpty()) {
            databaseRandomGenerator.generateCarMainDBRecords(50);
        }
    }

}
