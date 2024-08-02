package org.example;

import org.example.enums.Color;
import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.impl.CarServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CarService carService= new CarServiceImpl();

        carService.createCarTable();

        System.out.println(carService.saveCar(new Car( "Toyota", "Camry", LocalDate.of(2020, 1, 1), 30000, Color.RED)));
        System.out.println(carService.saveCar(new Car( "Honda", "Civic", LocalDate.of(2019, 5, 15), 20000, Color.BLUE)));
        System.out.println(carService.saveCar(new Car( "Ford", "Focus", LocalDate.of(2021, 3, 10), 25000, Color.BLACK)));


        List<Car> HondaCivicCars = carService.getCarsBySearch("Honda", "Civic");
        System.out.println("Honda Civic cars:");
        for (Car car : HondaCivicCars) {
            System.out.println(car);
        }


        List<Car> carsInRange = carService.getCarsByPriceRange(BigDecimal.valueOf(20000), BigDecimal.valueOf(30000));
        System.out.println("Cars in Price Range:");
        for (Car car : carsInRange) {
            System.out.println(car);
        }


        List<Car> allCars = carService.getAllCars();
        System.out.println("All Cars:");
        for (Car car : allCars) {
            System.out.println("Id: " + car.getId() + ", Brand: " + car.getBrand() + ", Model: " + car.getModel() + ", Year: " +car.getYear() + ", Color: " + car.getColor());
        }


        Car updateCar = new Car();
        updateCar.setBrand("Honda");
        updateCar.setModel("Accord");
        updateCar.setYear(LocalDate.of(2021, 1, 1));
        updateCar.setPrice(28000);
        updateCar.setColor(Color.BLUE);
        System.out.println(carService.updateCar(1L, updateCar));


        carService.deleteCarById(1L);
        System.out.println("Deleted Car");


        }
    }






