package com.components.controllers;

import com.components.entities.Car;
import com.components.services.CarService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLCarQueryController implements GraphQLQueryResolver {

    private CarService carService;

    public GraphQLCarQueryController(CarService carService) {
        this.carService = carService;
    }

    public List<Car> getCars(final long count) {
        return this.carService.findMultiple(count);
    }

    public Car getCar(final long id) {
        return this.carService.findById(id);
    }

}
