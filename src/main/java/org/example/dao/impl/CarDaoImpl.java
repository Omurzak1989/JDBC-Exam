package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.CarDao;
import org.example.enums.Color;
import org.example.model.Car;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

    private final Connection connection= DatabaseConfig.getConnection();
    @Override
    public void createCarTable() {
        String sql="""
    create table if not exists cars (
    id serial primary key,
    brand varchar,
    model varchar,
    year date,
    price int,
    color varchar)
    """;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table successfully created");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String saveCar(Car car) {
        String sql= """
                insert into cars (brand,model,year,price,color)
                values(?,?,?,?,?)
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,car.getBrand());
            preparedStatement.setString(2,car.getModel());
            preparedStatement.setDate(3, Date.valueOf(car.getYear()));
            preparedStatement.setInt(4,car.getPrice());
            preparedStatement.setString(5,car.getColor().name());
            preparedStatement.executeUpdate();

            return "Car successfully saved";
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Error saving car: " + e.getMessage();
        }
    }

    @Override
    public Car getCarById(Long id) {
        String sql = "select * from cars where id=?";
        Car car = new Car();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                car.setId(resultSet.getLong("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getDate("year").toLocalDate());
                car.setPrice(resultSet.getInt("price"));
                car.setColor(Color.valueOf(resultSet.getString("color")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cars.add(new Car(resultSet.getLong("id"),
                                    resultSet.getString("brand"),
                                    resultSet.getString("model"),
                                    resultSet.getDate("year").toLocalDate(),
                                    resultSet.getInt("price"),
                                    Color.valueOf(resultSet.getString("color"))
                ));

            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cars;
    }

    @Override
    public String updateCar(Long carId, Car newCar) {
        String sql = """
                update cars
                set brand=?,model=?,year=?,price=?,color=?
                where id=?
                """;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,newCar.getBrand());
            preparedStatement.setString(2,newCar.getModel());
            preparedStatement.setDate(3, Date.valueOf(newCar.getYear()));
            preparedStatement.setInt(4,newCar.getPrice());
            preparedStatement.setString(5,newCar.getColor().name());
            preparedStatement.setLong(6,carId);
            int rowsUpdated=preparedStatement.executeUpdate();
            if(rowsUpdated>0){
                return "Car successfully updated";
            }else {
                return "Car not found";
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Error updating car" + e.getMessage();
        }
    }

    @Override
    public void deleteCarById(Long id) {
        String sql="delete from cars where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            int rowsDeleted=preparedStatement.executeUpdate();
            if(rowsDeleted>0){
                System.out.println("Car successfully deleted");
            }else {
                System.out.println("Car not found");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Car> getCarsBySearch(String brand, String model) {
        String sql = "select * from cars where brand=? and model=?";
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getDate("year").toLocalDate());
                car.setPrice(resultSet.getInt("price"));
                car.setColor(Color.valueOf(resultSet.getString("color")));
                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving cars by search: " + e.getMessage());
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        String sql = "select * from cars where price between ? and ?";
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, minPrice);
            preparedStatement.setBigDecimal(2, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getDate("year").toLocalDate());
                car.setPrice(resultSet.getInt("price"));
                car.setColor(Color.valueOf(resultSet.getString("color")));
                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cars;
    }
}
