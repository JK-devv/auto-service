package com.example.autoservice.service;

import com.example.autoservice.model.Car;
import java.util.List;

public interface CarService {
    Car create(Car car);

    Car update(Long carId, Car carRequest);

    List<Car> findByIdIn(List<Long> carsId);

    Car findById(Long id);
}
