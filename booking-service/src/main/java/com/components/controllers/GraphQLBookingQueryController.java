package com.components.controllers;

import com.components.entities.Car;
import com.components.services.BookingService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLBookingQueryController implements GraphQLQueryResolver {

    private BookingService carService;

    public GraphQLBookingQueryController(BookingService carService) {
        this.carService = carService;
    }

    public List<Car> getCars(final long count) {
        return this.carService.findMultiple(count);
    }

    public Car getCar(final long id) {
        return this.carService.findById(id);
    }

}

// @Autowired
//    private VehicleService vehicleService;
//    public List<Vehicle> getVehicles(final int count) {
//        return this.vehicleService.getAllVehicles(count);
//    }
//    public Optional<Vehicle> getVehicle(final int id) {
//        return this.vehicleService.getVehicle(id);
//    }