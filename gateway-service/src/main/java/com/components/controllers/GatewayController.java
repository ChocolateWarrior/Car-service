package com.components.controllers;

import com.components.dto.CarDto;
import com.components.dto.UserDTO;
import com.components.entities.Car;
import com.components.services.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private GatewayService gatewayService;

    @Autowired
    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody UserDTO userDTO){return  gatewayService.register(userDTO);}

    @GetMapping("/cars")
    public List<Car> getAll (){
        return gatewayService.getAll();
    }

    @GetMapping("/cars/{id}")
    public Car getById(@PathVariable("id") long id) {
        return gatewayService.getById(id);
    }

    @PostMapping("/book/{id}")
    public boolean book(@PathVariable("id") long id){
        return gatewayService.book(id);
    }

}
