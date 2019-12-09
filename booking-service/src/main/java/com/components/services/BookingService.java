package com.components.services;

import com.components.dto.CarDto;
import com.components.entities.Car;
import com.components.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookingService {

    private CarRepository carRepository;
    private String backPrefix;
    private RestTemplate restTemplate;

    @Autowired
    public BookingService(CarRepository carRepository,
                          @Value("${url.main-service}") String backPrefix,
                          RestTemplateBuilder restTemplateBuilder) {
        this.carRepository = carRepository;
        this.backPrefix = backPrefix;
        this.restTemplate= restTemplateBuilder.build();
    }

    private boolean isCarBooked(Long id) {
        return carRepository.findById(id).isPresent();
    }

    private boolean isCarPresentInMainService(Long id){

        try {
            ResponseEntity<CarDto> answer = restTemplate
                    .getForEntity(backPrefix + id, CarDto.class);
            return answer.getBody() != null;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean book(CarDto carDto) {
        if (isCarBooked(carDto.getId())) {
            return false;
        }
        if (!isCarPresentInMainService(carDto.getId())) {
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
