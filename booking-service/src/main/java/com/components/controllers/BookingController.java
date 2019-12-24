package com.components.controllers;

import com.components.dto.CarDto;
import com.components.entities.Car;
import com.components.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/book/{id}", consumes = "application/json", produces = "application/json")
    public boolean book(@PathVariable("id") long id){
        System.out.println("now in book method of booking service");
        return bookingService.book(id);
    }

    @GetMapping
    public List<Car> getBookings(@RequestParam("count") int count){
        return bookingService.findMultiple(count);
    }

    @GetMapping({"id"})
    public Car getCarById(@PathVariable long id){
        return bookingService.findById(id);
    }
}
