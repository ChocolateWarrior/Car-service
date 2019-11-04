package com.components.services.impl;

import com.components.dtos.CarDto;
import com.components.entities.Car;
import com.components.exceptions.CarNotFoundException;
import com.components.repositories.CarRepository;
import com.components.services.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DatabaseSupplierService implements Supplier {

    private CarRepository carRepository;

    public DatabaseSupplierService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        return null;
    }

    @Override
    public Car updateById(long id, CarDto carDto) {
        Car carToUpdate = carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        updateCar(carToUpdate, carDto);
        carRepository.save(carToUpdate);
        return carToUpdate;
    }

    @Override
    public Car findById(long id) {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<Car> findByQuery(String query) {
        return carRepository.findAllByBrandOrModelContains(query, query);
    }

    private void updateCar(Car carToUpdate, CarDto carDto) {
        if (Objects.nonNull(carDto.getBrand()))
            carToUpdate.setBrand(carDto.getBrand());

        if (Objects.nonNull(carDto.getColor()))
            carToUpdate.setColor(carDto.getColor());

        if (Objects.nonNull(carDto.getDriveLayout()))
            carToUpdate.setDriveLayout(carDto.getDriveLayout());

        if (Objects.nonNull(carDto.getTransmission()))
            carToUpdate.setTransmission(carDto.getTransmission());

        if (Objects.nonNull(carDto.getEngine()))
            carToUpdate.setEngine(carDto.getEngine());

        if (Objects.nonNull(carDto.getEngineCapacity()))
            carToUpdate.setEngineCapacity(carDto.getEngineCapacity());

        if (Objects.nonNull(carDto.getModel()))
            carToUpdate.setModel(carDto.getModel());

        if (Objects.nonNull(carDto.getPrice()))
            carToUpdate.setPrice(carDto.getPrice());
    }

}
