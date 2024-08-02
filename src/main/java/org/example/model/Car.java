package org.example.model;

import lombok.*;
import org.example.enums.Color;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car {
    private Long id;
    private String brand;
    private String model;
    private LocalDate year;
    private int price;
    private Color color;

    public Car(String brand, String model, LocalDate year, int price, Color color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
    }
}
