package com.components.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "cars", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Car {

    @Id
    @SequenceGenerator(name="seq_car", allocationSize = 0, sequenceName = "sequence_car_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_car")
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "engine", nullable = false)
    private String engine;

    @Column(name = "engine_capacity", nullable = false)
    private BigDecimal engineCapacity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "transmission", nullable = false)
    private Transmission transmission;

    @Column(name = "drive_layout", nullable = false)
    private DriveLayout driveLayout;
}
