package com.example.autoservice.mapper;

import com.example.autoservice.model.Car;
import com.example.autoservice.model.CarOwner;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.dto.request.CarOwnerRequestDto;
import com.example.autoservice.model.dto.response.CarOwnerResponseDto;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.OrderService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarOwnerMapper implements RequestMapper<CarOwner, CarOwnerRequestDto>,
        ResponseMapper<CarOwner, CarOwnerResponseDto> {
    private final CarService carService;
    private final OrderService orderService;

    @Override
    public CarOwner mapToModel(CarOwnerRequestDto dto) {
        return CarOwner.builder()
                .cars(carService.findByIdIn(dto.getCarsId()))
                .orders(orderService.findByIdIn(dto.getOrdersId()))
                .build();
    }

    @Override
    public CarOwnerResponseDto mapToResponse(CarOwner model) {
        return CarOwnerResponseDto.builder()
                .id(model.getId())
                .carsId(model.getCars().stream()
                        .map(Car::getId)
                        .collect(Collectors.toList()))
                .ordersId(model.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()))
                .build();

    }
}
