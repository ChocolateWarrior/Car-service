package com.components.service;

import com.components.entity.Car;
import com.components.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    //TODO rewrite to Collectors.toMap
    public Map<Long, BigDecimal> getIdAndPrice() {
        Map<Long, BigDecimal> idPriceMap= new HashMap<>();
        carRepository.findAll().forEach(e -> idPriceMap.put(e.getId(), e.getPrice()));
        return idPriceMap;
    }

    public Car getById(long id) {
        return carRepository.findById(id).orElseThrow();
    }

}
