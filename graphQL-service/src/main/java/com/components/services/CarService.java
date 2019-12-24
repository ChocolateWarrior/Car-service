package com.components.services;

import com.components.dtos.CarDto;
import com.components.entities.Car;
import com.components.entities.DriveLayout;
import com.components.entities.Transmission;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CarService {

    private RestTemplate restTemplate;
    private final String MAIN_SERVICE_URL = "localhost:8090/cars";


    public CarService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String createByMutation(String brand,
                                String model,
                                String color,
                                String engine,
                                String engineCapacity,
                                String price,
                                String transmission,
                                String driveLayout) {

        HttpHeaders headers = new HttpHeaders();
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

        HttpEntity<String> request = new HttpEntity<String>(carToCreate.toString(), headers);
        URI uri = restTemplate.postForLocation(MAIN_SERVICE_URL + "", request);

        return "New car was created successfully!";

    }

    public String updateByMutation(String id,
                                String brand,
                                String model,
                                String color,
                                String engine,
                                String engineCapacity,
                                String price,
                                String transmission,
                                String driveLayout) {

        HttpHeaders headers = new HttpHeaders();
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

        HttpEntity<CarDto> requestUpdate =
                new HttpEntity<>(carDto, headers);
        String resourceUrl = MAIN_SERVICE_URL + "/" + id;

        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);

        return "Car with id " + id + " was updated successfully!";

    }

    public String deleteByMutation(String id) {

        HttpHeaders headers = new HttpHeaders();
        String resourceUrl = MAIN_SERVICE_URL + "/" + id;

        restTemplate.delete(resourceUrl);

        return "Car with id " + id + " was deleted successfully!";

    }

    public List<Car> findMultiple(long count) {

        try {
            ResponseEntity<List<Car>> answer = restTemplate
                    .exchange(
                            MAIN_SERVICE_URL +
                                    "?count=" + count,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Car>>() {
                            });
            return answer.getBody();
        } catch (Exception e) {

        }
        return new ArrayList<>();

    }

    public Car findById(long id) {

        try {
            ResponseEntity<Car> answer = restTemplate
                    .getForEntity(MAIN_SERVICE_URL +
                            "/" + id, Car.class);
            return answer.getBody();
        } catch (Exception e) {

        }
        return new Car();
    }
}
