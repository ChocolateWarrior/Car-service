package com.components.controllers;

import com.components.dtos.CarDto;
import com.components.entities.Car;
import com.components.services.CarService;
import com.components.services.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Value("${url.supplier1}")
    private String backPrefixForFirstSupplier;
    @Value("${url.supplier2}")
    private String backPrefixForSecondSupplier;

    private Supplier carService;

    @Autowired
    public CarController(Supplier carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car) {
        return carService.create(car);
    }

    @GetMapping
    public List<Car> getAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") long id) {
        return carService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        carService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Car updateById(@PathVariable("id") long id, @RequestBody CarDto carDto) {
        return carService.updateById(id, carDto);
    }

    @GetMapping("/all")
    public List<Car> getAllCars(@RequestParam String query) {
        return carService.findByQuery(query);
    }

}
