package com.components.controllers;

import com.components.dto.CarDto;
import com.components.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/book", consumes = "application/json", produces = "application/json")
    public boolean book(@RequestBody CarDto carDto){
        System.out.println("now in book method of booking service");
        return bookingService.book(carDto);
    }
}
