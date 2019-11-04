package com.components.generators;

import com.components.repositories.CarRepository;

public class DatabaseRandomGenerator {

    private CarRepository carRepository;

    public DatabaseRandomGenerator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


}
