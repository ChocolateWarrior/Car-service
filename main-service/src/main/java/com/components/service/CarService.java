package com.components.service;

import com.components.dto.CarDto;
import com.components.entity.Car;
import com.components.exception.CarNotFoundException;
import com.components.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CarService {

    @Value("${url.supplier1}")
    private String backPrefixForFirstSupplier;

    @Value("${url.supplier2}")
    private String backPrefixForSecondSupplier;

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

    public Map<Long, BigDecimal> getPriceListFromFirstSupplier(){
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map<Long, BigDecimal>> answer = restTemplate.exchange(
                    backPrefixForFirstSupplier +
                            "/price-list",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<Long, BigDecimal>>() {
                    });
            return answer.getBody();
        } catch (Exception e){}

        return new HashMap<>();
    }

    public Car getDetailsFromFirstSupplier(long id){
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Car> answer = restTemplate.getForEntity(backPrefixForFirstSupplier +
                    "/details/" + id, Car.class);
            return answer.getBody();
        } catch (Exception e){

        }
        return new Car();
    }

    public List<Car> getCarFromSecondSupplier(String query){
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<List<Car>> answer = restTemplate.exchange(
                    backPrefixForSecondSupplier +
                            "/" + query,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Car>>() {
                    });
            return answer.getBody();
        } catch (Exception e){

        }
        return new ArrayList<>();
    }

}
