package com.components.service;

import com.components.dto.CarDto;
import com.components.entity.Car;
import com.components.exception.CarNotFoundException;
import com.components.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car findById(long id) {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public void deleteById(long id){
        carRepository.deleteById(id);
    }

    public Car updateById(long id, CarDto carDto) {
        Car carToUpdate = carRepository.findById(id).orElseThrow(CarNotFoundException::new);
        updateCar(carToUpdate, carDto);
        carRepository.save(carToUpdate);
        return carToUpdate;
    }

    private void updateCar(Car carToUpdate, CarDto carDto){
        carToUpdate.setBrand(carDto.getBrand());
        carToUpdate.setColor(carDto.getColor());
        carToUpdate.setDriveLayout(carDto.getDriveLayout());
        carToUpdate.setTransmission(carDto.getTransmission());
        carToUpdate.setEngine(carDto.getEngine());
        carToUpdate.setEngineCapacity(carDto.getEngineCapacity());
        carToUpdate.setModel(carDto.getModel());
        carToUpdate.setPrice(carDto.getPrice());
    }
}
