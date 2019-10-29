package com.components.controllers;

import com.components.entities.Car;
import com.components.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchByQueryController {
    private final CarService carService;

    @Autowired
    public SearchByQueryController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAll(){
        return carService.getAllProducts();
    }

    @GetMapping("/by_query")
    public List<Car> getProductsByQuery(@RequestParam String query) {
        return carService.getAllProductsByQuery(query);
    }
}
