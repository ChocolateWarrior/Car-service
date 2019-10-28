package com.components.service;

import com.components.entity.Car;
import com.components.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Map<Long, BigDecimal> getIdAndPrice() {
        return carRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Car::getId, Car::getPrice));
    }

    public Car getById(long id) {
        return carRepository.findById(id).orElseThrow();
    }

}
