package com.components.controller;

import com.components.entity.Car;
import com.components.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping
    public List<Car> getProductsByQuery(@RequestParam String query) {
        return carService.getAllProductsByQuery(query);
    }
}
