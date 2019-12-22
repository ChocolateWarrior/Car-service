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
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;


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

    private CarDto getCarFromMainService(Long id){

        try {
            ResponseEntity<CarDto> answer = restTemplate
                    .getForEntity(
//                            backPrefix +
                            "http://localhost:8090" +
                                    "/" + id, CarDto.class);
            return answer.getBody();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean book(long id) {

        CarDto carDto;
        if (isCarBooked(id)) {
            return false;
        }
        if (getCarFromMainService(id) != null) {
            carDto = getCarFromMainService(id);
        } else {
            return false;
        }

        Car car = Car
                .builder()
                .id(Objects.requireNonNull(carDto).getId())
                .brand(Objects.requireNonNull(carDto).getBrand())
                .model(Objects.requireNonNull(carDto).getModel())
                .color(Objects.requireNonNull(carDto).getColor())
                .engine(Objects.requireNonNull(carDto).getEngine())
                .engineCapacity(Objects.requireNonNull(carDto).getEngineCapacity())
                .price(Objects.requireNonNull(carDto).getPrice())
                .transmission(Objects.requireNonNull(carDto).getTransmission())
                .driveLayout(Objects.requireNonNull(carDto).getDriveLayout())
                .build();
        carRepository.save(car);
        return true;
    }

    public List<Car> findMultiple(long count) {
        return carRepository.findAll()
                .stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    public Car findById(long id) {
        return carRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Car bookByMutation(long id) {
        System.out.println("Booking car with id: " + id);
        book(id);
        return new Car();
    }
}
