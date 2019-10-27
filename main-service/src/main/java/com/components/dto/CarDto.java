package com.components.dto;

import com.components.entity.DriveLayout;
import com.components.entity.Transmission;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CarDto {

    private String brand;
    private String model;
    private String color;
    private String engine;
    private BigDecimal engineCapacity;
    private BigDecimal price;
    private Transmission transmission;
    private DriveLayout driveLayout;

}
