package org.example.dao;

import org.example.model.Car;

import java.math.BigDecimal;
import java.util.List;

public interface CarDao {
    void createCarTable();
    String saveCar(Car car);
    Car getCarById(Long carId);
    List<Car> getAllCars();
    String updateCar(Long carId, Car newCar);
    void deleteCarById(Long id);
    List<Car> getCarsBySearch(String brand, String model);
    List<Car> getCarsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

}
