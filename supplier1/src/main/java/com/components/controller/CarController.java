package com.components.controller;

import com.components.entity.Car;
import com.components.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/supplier1")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/price-list")
    public Map<Long, BigDecimal> getIdAndPrice() {
        return carService.getIdAndPrice();
    }

    @GetMapping("/details/{id}")
    public Car getCarById(@PathVariable("id") long id) {
        return carService.getById(id);
    }
}
