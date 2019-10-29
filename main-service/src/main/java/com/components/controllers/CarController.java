package com.components.controllers;

import com.components.dtos.CarDto;
import com.components.entities.Car;
import com.components.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping
    public List<Car> getAll(){
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") long id){
        return carService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        carService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Car updateById(@PathVariable("id") long id, @RequestBody CarDto carDto){
        return carService.updateById(id, carDto);
    }

    @GetMapping("/supplier1/price-list")
    public Map<Long, BigDecimal> getPriceList(){
        return carService.getPriceListFromFirstSupplier();
    }

    @GetMapping("/supplier1/details/{id}")
    public Car getDetailsById(@PathVariable("id") long id){
        return carService.getDetailsFromFirstSupplier(id);
    }

    @GetMapping("/all")
    public List<Car> getAllCars(@RequestParam String query){
        Map<Long, BigDecimal> priceList = getPriceList();
        List<Car> list = priceList.keySet()
                .stream()
                .map(this::getById)
                .collect(Collectors.toList());
        List<Car> searchList = filterByQuery(query);
        return list
                .stream()
                .filter(searchList::contains)
                .collect(Collectors.toList());

    }

    @GetMapping("/supplier2")
    public List<Car> filterByQuery(@RequestParam String query){
        return carService.getCarFromSecondSupplier(query);
    }

}
