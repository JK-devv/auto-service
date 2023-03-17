package com.example.autoservice.service.impl;

import com.example.autoservice.model.Car;
import com.example.autoservice.repository.CarRepository;
import com.example.autoservice.service.CarService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Car findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can`t find car by id: " + id));
    }

    @Override
    public Car create(Car car) {
        return repository.save(car);
    }

    @Override
    public Car update(Long carId, Car carRequest) {
        Car existedCar = findById(carId);
        existedCar.setOwner(carRequest.getOwner());
        existedCar.setYear(carRequest.getYear());
        existedCar.setMark(carRequest.getMark());
        existedCar.setNumber(carRequest.getNumber());
        existedCar.setModel(carRequest.getModel());
        return repository.save(existedCar);
    }

    @Override
    public List<Car> findByIdIn(List<Long> carsId) {
        return repository.findByIdIn(carsId);
    }
}
