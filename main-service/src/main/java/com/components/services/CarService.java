package com.components.services;

import com.components.dtos.CarDto;
import com.components.entities.Car;
import com.components.entities.DriveLayout;
import com.components.entities.Transmission;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarService implements Supplier {

    private Supplier databaseSupplierService;
    private Supplier firstSupplierService;
    private Supplier secondSupplierService;

    @Autowired
    public CarService(Supplier databaseSupplierService,
                      Supplier firstSupplierService,
                      Supplier secondSupplierService) {
        this.databaseSupplierService = databaseSupplierService;
        this.firstSupplierService = firstSupplierService;
        this.secondSupplierService = secondSupplierService;

    }

    public List<Car> findAll() {
        return databaseSupplierService.findAll();
    }

    public Car findById(long id) {
        return databaseSupplierService.findById(id);
    }

    public void deleteById(long id) {
        System.out.println("DeleteById id: " + id);
        databaseSupplierService.deleteById(id);
    }

    public Car updateById(long id, CarDto carDto) {
        return databaseSupplierService.updateById(id, carDto);
    }

    public Car create(Car car) {
        return databaseSupplierService.create(car);
    }

    public List<Car> findByQuery(String query) {
        List<Car> resultList = new ArrayList<>();

        resultList.addAll(databaseSupplierService.findByQuery(query));
        resultList.addAll(firstSupplierService.findByQuery(query));
        resultList.addAll(secondSupplierService.findByQuery(query));

        return resultList;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void handleContextRefreshEvent() {
        System.out.println("Before cashing");
        List<Car> result = findByQuery("BMW");
        System.out.println("After cashing: " + result.size());
    }

    public List<Car> findMultiple(long count) {
        return this.findByQuery("")
                .stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    @Transactional
    public Car createByMutation(String brand, String model,
                                String color, String engine,
                                String engineCapacity, String price,
                                String transmission, String driveLayout) {

        Car carToCreate = Car.builder()
                .brand(brand)
                .model(model)
                .color(color)
                .engine(engine)
                .engineCapacity(new BigDecimal(engineCapacity))
                .price(new BigDecimal(price))
                .transmission(Transmission.valueOf(transmission))
                .driveLayout(DriveLayout.valueOf(driveLayout))
                .build();

        System.out.println(carToCreate);
        return databaseSupplierService.create(carToCreate);

    }

    public Car updateByMutation(String id,
                                String brand,
                                String model,
                                String color,
                                String engine,
                                String engineCapacity,
                                String price,
                                String transmission,
                                String driveLayout) {

        CarDto carDto = new CarDto();
        if(Objects.nonNull(brand))
            carDto.setBrand(brand);

        if(Objects.nonNull(model))
            carDto.setModel(model);

        if(Objects.nonNull(color))
            carDto.setColor(color);

        if(Objects.nonNull(engine))
            carDto.setEngine(engine);

        if(Objects.nonNull(engineCapacity))
            carDto.setEngineCapacity(new BigDecimal(engineCapacity));

        if(Objects.nonNull(price))
            carDto.setPrice(new BigDecimal(price));

        if(Objects.nonNull(transmission))
            carDto.setTransmission(Transmission.valueOf(transmission));

        if(Objects.nonNull(driveLayout))
            carDto.setDriveLayout(DriveLayout.valueOf(driveLayout));

        return updateById(Long.valueOf(id), carDto);
    }

    public String deleteByMutation(String id) {
        deleteById(Long.valueOf(id));
        return "Deleted car with id:" + id + " successfully!";
    }
}
