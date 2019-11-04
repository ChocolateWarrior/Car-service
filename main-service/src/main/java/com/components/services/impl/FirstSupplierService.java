package com.components.services.impl;

import com.components.dtos.CarDto;
import com.components.entities.Car;
import com.components.services.Supplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FirstSupplierService implements Supplier {

    private String backPrefix;
    private RestTemplate restTemplate;

    public FirstSupplierService(@Value("${url.supplier1}") String backPrefix,
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
    public List<Car> findByQuery(String query) {
        Map<Long, BigDecimal> priceList = getPriceListFromFirstSupplier();
        return priceList.keySet()
                .stream()
                .map(this::getDetailsFromFirstSupplier)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {

    }

    private Car getDetailsFromFirstSupplier(long id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Car> answer = restTemplate
                    .getForEntity(backPrefix +
                            "/details/" + id, Car.class);
            return answer.getBody();
        } catch (Exception e) {

        }
        return new Car();
    }

    private Map<Long, BigDecimal> getPriceListFromFirstSupplier() {

        try {
            ResponseEntity<Map<Long, BigDecimal>> answer = restTemplate
                    .exchange(
                            backPrefix +
                                    "/price-list",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<Map<Long, BigDecimal>>() {
                            });
            return answer.getBody();
        } catch (Exception e) {
        }

        return new HashMap<>();
    }

}
