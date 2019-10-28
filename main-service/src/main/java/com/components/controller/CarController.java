package com.components.controller;

import com.components.dto.CarDto;
import com.components.entity.Car;
import com.components.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping
    public List<Car> getAll(){
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") long id){
        return carService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        carService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Car updateById(@PathVariable("id") long id, @RequestBody CarDto carDto){
        return carService.updateById(id, carDto);
    }

    @GetMapping("/supplier1/price-list")
    public Map<Long, BigDecimal> getPriceList(){
        return carService.getPriceListFromFirstSupplier();
    }

    @GetMapping("/supplier1/details/{id}")
    public Car getDetailsById(@PathVariable("id") long id){
        return carService.getDetailsFromFirstSupplier(id);
    }

}
