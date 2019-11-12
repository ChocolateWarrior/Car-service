package com.components.services.impl;

import com.components.dtos.CarDto;
import com.components.dtos.FirstSupplierCarDto;
import com.components.entities.Car;
import com.components.services.Supplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    @Cacheable(value = "firstSupplier", key = "#query.trim()")
    public List<Car> findByQuery(String query) {
        Map<Long, BigDecimal> priceList = getPriceListFromFirstSupplier();
        return priceList.keySet()
                .stream()
                .map(this::getDetailsFromFirstSupplier)
                .map(this::unify)
                .filter(e -> e.getBrand().equals(query) ||
                        e.getModel().equals(query))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {

    }

    private FirstSupplierCarDto getDetailsFromFirstSupplier(long id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<FirstSupplierCarDto> answer = restTemplate
                    .getForEntity(backPrefix +
                            "/details/" + id, FirstSupplierCarDto.class);
            return answer.getBody();
        } catch (Exception e) {

        }
        return new FirstSupplierCarDto();
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

    private Car unify(FirstSupplierCarDto firstSupplierCarDto) {

        Car unifiedCar = new Car();

        if (Objects.nonNull(firstSupplierCarDto.getBrand()))
            unifiedCar.setBrand(firstSupplierCarDto.getBrand());

        if (Objects.nonNull(firstSupplierCarDto.getColor()))
            unifiedCar.setColor(firstSupplierCarDto.getColor());

        if (Objects.nonNull(firstSupplierCarDto.getDriveLayout()))
            unifiedCar.setDriveLayout(firstSupplierCarDto.getDriveLayout());

        if (Objects.nonNull(firstSupplierCarDto.getTransmission()))
            unifiedCar.setTransmission(firstSupplierCarDto.getTransmission());

        if (Objects.nonNull(firstSupplierCarDto.getEngine()))
            unifiedCar.setEngine(firstSupplierCarDto.getEngine());

        if (Objects.nonNull(firstSupplierCarDto.getModel()))
            unifiedCar.setModel(firstSupplierCarDto.getModel());

        if (Objects.nonNull(firstSupplierCarDto.getPrice()))
            unifiedCar.setPrice(firstSupplierCarDto.getPrice());

        return unifiedCar;
    }

    @Scheduled(cron = "0 0 12 * * *")
    @CacheEvict(value = "firstSupplier", allEntries = true)
    public void resetCache() {
    }
}
