package com.components.dtos;

import com.components.entities.DriveLayout;
import com.components.entities.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FirstSupplierCarDto {

    private String brand;
    private String model;
    private String color;
    private String engine;
    private BigDecimal price;
    private Transmission transmission;
    private DriveLayout driveLayout;
}
