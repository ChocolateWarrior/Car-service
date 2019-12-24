package com.components.resolvers.mutationResolvers;

import com.components.entities.Car;
import com.components.services.BookingService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class GraphQLBookingMutationController implements GraphQLMutationResolver {

    private BookingService carService;

    public GraphQLBookingMutationController(BookingService carService) {
        this.carService = carService;
    }

    public String bookCar(final String id) {
        return this.carService.bookByMutation(Long.valueOf(id));
    }

}

