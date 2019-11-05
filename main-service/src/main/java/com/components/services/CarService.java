package com.components.services;

import com.components.dtos.CarDto;
import com.components.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        resultList.forEach(System.out::println);

        return resultList;
    }

}
