package com.components.dtos;

import com.components.entities.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SecondSupplierCarDto {
    private String brand;
    private String model;
    private String color;
    private BigDecimal price;
    private Transmission transmission;
}
