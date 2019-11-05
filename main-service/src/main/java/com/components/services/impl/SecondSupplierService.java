package com.components.services.impl;

import com.components.dtos.CarDto;
import com.components.dtos.SecondSupplierCarDto;
import com.components.entities.Car;
import com.components.services.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SecondSupplierService implements Supplier {

    private String backPrefix;
    private RestTemplate restTemplate;

    @Autowired
    public SecondSupplierService(@Value("${url.supplier2}") String backPrefix,
                                 RestTemplateBuilder restTemplate) {
        this.backPrefix = backPrefix;
        this.restTemplate = restTemplate.build();
    }

    @Override
    public Car create(Car car) {
        return null;
    }

    @Override
    public Car updateById(long id, CarDto carDto) {
        return null;
    }

    @Override
    public Car findById(long id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public List<Car> findByQuery(String query){
        return getSecondSupplierList(query).stream()
                .map(this::unify)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
    }

    private List<SecondSupplierCarDto> getSecondSupplierList(String query) {
        try {
            ResponseEntity<List<SecondSupplierCarDto>> answer = restTemplate
                    .exchange(
                            backPrefix +
                                    "/by_query?query=" + query,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<SecondSupplierCarDto>>() {
                            });
            return answer.getBody();
        } catch (Exception e) {

        }
        return new ArrayList<>();
    }

    private Car unify(SecondSupplierCarDto secondSupplierCarDto) {

        Car unifiedCar = new Car();

        if (Objects.nonNull(secondSupplierCarDto.getBrand()))
            unifiedCar.setBrand(secondSupplierCarDto.getBrand());

        if (Objects.nonNull(secondSupplierCarDto.getColor()))
            unifiedCar.setColor(secondSupplierCarDto.getColor());

        if (Objects.nonNull(secondSupplierCarDto.getTransmission()))
            unifiedCar.setTransmission(secondSupplierCarDto.getTransmission());

        if (Objects.nonNull(secondSupplierCarDto.getModel()))
            unifiedCar.setModel(secondSupplierCarDto.getModel());

        if (Objects.nonNull(secondSupplierCarDto.getPrice()))
            unifiedCar.setPrice(secondSupplierCarDto.getPrice());

        return unifiedCar;
    }

}
