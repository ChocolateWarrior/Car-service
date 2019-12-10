package com.components.controllers;

import com.components.dto.CarDto;
import com.components.dto.UserDTO;
import com.components.entities.Car;
import com.components.services.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gateway")
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

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") long id) {
        return gatewayService.getById(id);
    }

    @PostMapping
    public boolean book(@RequestParam long id){
        System.out.println("now in book method of gateway service");
        return gatewayService.book(id);
    }

}
