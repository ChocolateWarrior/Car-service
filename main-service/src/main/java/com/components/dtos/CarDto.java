package com.components.dtos;

import com.components.entities.DriveLayout;
import com.components.entities.Transmission;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
