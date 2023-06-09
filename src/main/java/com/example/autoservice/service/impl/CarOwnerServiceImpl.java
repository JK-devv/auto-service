package com.example.autoservice.service.impl;

import com.example.autoservice.model.CarOwner;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.model.Task;
import com.example.autoservice.repository.CarOwnerRepository;
import com.example.autoservice.service.CarOwnerService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarOwnerServiceImpl implements CarOwnerService {
    private static final int PRODUCT_DISCOUNT = 1;
    private static final int TASK_DISCOUNT = 2;
    private final CarOwnerRepository repository;

    @Override
    public CarOwner create(CarOwner carOwnerRequest) {
        return repository.save(carOwnerRequest);
    }

    @Override
    public CarOwner findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can`t find car owner by id: " + id));
    }

    @Override
    public List<Order> getOrders(Long carOwnerId) {
        return repository.getOrdersByCarOwnerId(carOwnerId);
    }

    @Override
    public CarOwner update(Long carOwnerId, CarOwner carOwnerRequest) {
        CarOwner existedCar = findById(carOwnerId);
        existedCar.setCars(carOwnerRequest.getCars());
        existedCar.setOrders(carOwnerRequest.getOrders());
        return repository.save(existedCar);
    }

    @Override
    public Double getTotalPrice(Long carOwnerId) {
        List<Order> orders = repository.getOrdersWithTasksAndProductByCarOwnerId(carOwnerId);
        Double ordersPrice = orders.stream()
                .map(Order::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        double productPriceSum = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();

        double productDiscount = (productPriceSum * (orders.size() * PRODUCT_DISCOUNT)) / 100;

        double taskPriceSum = orders.stream()
                .flatMap(order -> order.getTasks().stream())
                .map(Task::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        double taskDiscount = (taskPriceSum * (orders.size() * TASK_DISCOUNT)) / 100;
        return ordersPrice - productDiscount - taskDiscount;
    }
}
