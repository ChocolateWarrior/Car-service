package com.components.dto;

import com.components.entities.DriveLayout;
import com.components.entities.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private String engine;
    private BigDecimal engineCapacity;
    private BigDecimal price;
    private Transmission transmission;
    private DriveLayout driveLayout;

}
