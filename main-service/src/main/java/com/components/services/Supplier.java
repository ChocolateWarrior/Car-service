package com.components.services;

import com.components.dtos.CarDto;
import com.components.entities.Car;

import java.util.List;

public interface Supplier {

    Car create(Car car);

    Car updateById(long id, CarDto carDto);

    Car findById(long id);

    List<Car> findAll();

    List<Car> findByQuery(String query);

    void deleteById(long id);

}
