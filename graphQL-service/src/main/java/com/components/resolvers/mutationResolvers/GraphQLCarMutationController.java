package com.components.resolvers.mutationResolvers;

import com.components.entities.Car;
import com.components.services.CarService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class GraphQLCarMutationController implements GraphQLMutationResolver {

    private CarService carService;

    public GraphQLCarMutationController(CarService carService) {
        this.carService = carService;
    }

    public String createCar(final String brand,
                         final String model,
                         final String color,
                         final String engine,
                         final String engineCapacity,
                         final String price,
                         final String transmission,
                         final String driveLayout) {

        System.out.println("IN CAR CREATE MUTATION");
        return this.carService.createByMutation(brand, model, color,
                engine, engineCapacity, price, transmission, driveLayout);
    }

    public String updateCar(final String id,
                         final String brand,
                         final String model,
                         final String color,
                         final String engine,
                         final String engineCapacity,
                         final String price,
                         final String transmission,
                         final String driveLayout) {

        System.out.println("IN CAR UPDATE MUTATION");
        return this.carService.updateByMutation(id, brand, model, color,
                engine, engineCapacity, price, transmission, driveLayout);
    }

    public String deleteCar(final String id) {

        System.out.println("IN CAR DELETE MUTATION");
        return this.carService.deleteByMutation(id);
    }

}

