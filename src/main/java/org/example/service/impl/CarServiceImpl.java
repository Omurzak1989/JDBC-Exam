package org.example.service.impl;

import org.example.dao.CarDao;
import org.example.dao.impl.CarDaoImpl;
import org.example.model.Car;
import org.example.service.CarService;

import java.math.BigDecimal;
import java.util.List;

public class CarServiceImpl implements CarService {
    CarDao carDao = new CarDaoImpl();
    @Override
    public void createCarTable() {
        carDao.createCarTable();

    }

    @Override
    public String saveCar(Car car) {
        return carDao.saveCar(car);
    }

    @Override
    public Car getCarById(Long id) {
        return carDao.getCarById(id);
    }

    @Override
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Override
    public String updateCar(Long carId, Car newCar) {
        return carDao.updateCar(carId, newCar);
    }

    @Override
    public void deleteCarById(Long id) {
        carDao.deleteCarById(id);
    }

    @Override
    public List<Car> getCarsBySearch(String brand, String model) {
        return carDao.getCarsBySearch(brand, model);
    }

    @Override
    public List<Car> getCarsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return carDao.getCarsByPriceRange(minPrice, maxPrice);
    }
}
