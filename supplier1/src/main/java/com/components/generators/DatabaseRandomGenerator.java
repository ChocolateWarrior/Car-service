package com.components.generators;

import com.components.entities.Car;
import com.components.entities.DriveLayout;
import com.components.entities.Transmission;
import com.components.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseRandomGenerator {

    private CarRepository carRepository;
    private List<String> brands;
    private List<String> models;
    private List<String> colors;
    private List<String> engines;
    private List<BigDecimal> engineCapacities;
    private List<BigDecimal> prices;
    private List<DriveLayout> driveLayouts;
    private List<Transmission> transmissions;
    private Random random;

    @Autowired
    public DatabaseRandomGenerator(CarRepository carRepository) {
        this.carRepository = carRepository;
        random = new Random();
        brands = new ArrayList<>(Arrays.asList("KIA", "BMW", "AUDI", "TOYOTA", "RENAULT"));
        models = new ArrayList<>(Arrays.asList("X1", "X3", "X5", "X7", "X9"));
        colors = new ArrayList<>(Arrays.asList("BLACK", "WHITE", "GOLD", "GREY", "GREEN"));
        engines = new ArrayList<>(Arrays.asList("mt1", "mt2", "mt3", "mt4", "mt5"));
        engineCapacities = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(2), BigDecimal.valueOf(2.4), BigDecimal.valueOf(3.4), BigDecimal.valueOf(5), BigDecimal.valueOf(90)));
        prices = new ArrayList<>(Arrays.asList(BigDecimal.valueOf(200000), BigDecimal.valueOf(300000), BigDecimal.valueOf(400000), BigDecimal.valueOf(500000), BigDecimal.valueOf(9000000)));
        driveLayouts = new ArrayList<>(Arrays.asList(DriveLayout.values()));
        transmissions = new ArrayList<>(Arrays.asList(Transmission.values()));
    }

    public void generateCarMainDBRecords(int recordsAmount) {
        for (int i = 0; i < recordsAmount; i++) {
            Car check = Car
                    .builder()
                    .brand(brands.get(getRandomIndex(brands)))
                    .model(models.get(getRandomIndex(models)))
                    .color(colors.get(getRandomIndex(colors)))
                    .engine(engines.get(getRandomIndex(engines)))
                    .engineCapacity(engineCapacities.get(getRandomIndex(engineCapacities)))
                    .price(prices.get(getRandomIndex(prices)))
                    .driveLayout(driveLayouts.get(getRandomIndex(driveLayouts)))
                    .transmission(transmissions.get(getRandomIndex(transmissions)))
                    .build();
            carRepository.save(check);
        }
    }


    private int getRandomIndex(List givenList) {
        return random.nextInt(givenList.size());
    }

}
