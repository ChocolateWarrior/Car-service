package com.components.services;

import com.components.entities.Car;
import com.components.generators.DatabaseRandomGenerator;
import com.components.repositories.CarRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CarService {
    private CarRepository carRepository;
    private DatabaseRandomGenerator databaseRandomGenerator;

    public CarService(CarRepository carRepository, DatabaseRandomGenerator databaseRandomGenerator) {
        this.carRepository = carRepository;
        this.databaseRandomGenerator = databaseRandomGenerator;
    }

    public List<Car> getAllProducts() {
        return carRepository.findAll();
    }

    public List<Car> getAllProductsByQuery(String query) {
//        delayResponse(15);
        return carRepository.findAllByBrandOrModelContains(query, query);
    }
    private List<Car> findAll(){return carRepository.findAll();}

    private void delayResponse(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void generateDB() {
        if (findAll().isEmpty()) {
            databaseRandomGenerator.generateCarMainDBRecords(10);
        }
    }
}