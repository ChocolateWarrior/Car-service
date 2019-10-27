package com.components.service;

import com.components.dto.CarDto;
import com.components.entity.Car;
import com.components.exception.CarNotFoundException;
import com.components.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Car addCar(Car car) {
        carRepository.save(car);
        return car;
    }

    private void updateCar(Car carToUpdate, CarDto carDto){
        if(Objects.nonNull(carDto.getBrand()))
            carToUpdate.setBrand(carDto.getBrand());

        if(Objects.nonNull(carDto.getColor()))
            carToUpdate.setColor(carDto.getColor());

        if(Objects.nonNull(carDto.getDriveLayout()))
            carToUpdate.setDriveLayout(carDto.getDriveLayout());

        if(Objects.nonNull(carDto.getTransmission()))
            carToUpdate.setTransmission(carDto.getTransmission());

        if(Objects.nonNull(carDto.getEngine()))
            carToUpdate.setEngine(carDto.getEngine());

        if(Objects.nonNull(carDto.getEngineCapacity()))
            carToUpdate.setEngineCapacity(carDto.getEngineCapacity());

        if(Objects.nonNull(carDto.getModel()))
            carToUpdate.setModel(carDto.getModel());

        if(Objects.nonNull(carDto.getPrice()))
            carToUpdate.setPrice(carDto.getPrice());
    }
}
