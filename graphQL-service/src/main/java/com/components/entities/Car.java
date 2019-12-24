package com.components.entities;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    private long id;

    private String brand;

    private String model;

    private String color;

    private String engine;

    private BigDecimal engineCapacity;

    private BigDecimal price;

    private Transmission transmission;

    private DriveLayout driveLayout;
}
