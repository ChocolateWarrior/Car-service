package com.components.services;

import com.components.dto.CarDto;
import com.components.entities.Car;
import com.components.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private CarRepository carRepository;

    @Autowired
    public BookingService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean isCarBooked(Long id) {
        return carRepository.findById(id).isPresent();
    }


    public boolean book(CarDto carDto) {
        if (isCarBooked(carDto.getId())) {
            return false;
        }
        Car car = Car
                .builder()
                .id(carDto.getId())
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .color(carDto.getColor())
                .engine(carDto.getEngine())
                .engineCapacity(carDto.getEngineCapacity())
                .price(carDto.getPrice())
                .transmission(carDto.getTransmission())
                .driveLayout(carDto.getDriveLayout())
                .build();
        carRepository.save(car);
        return true;
    }


}
