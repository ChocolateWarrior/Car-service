package com.components.dto;

import com.components.entities.DriveLayout;
import com.components.entities.Transmission;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
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
