package com.example.autoservice.mapper;

import com.example.autoservice.model.Car;
import com.example.autoservice.model.dto.request.CarRequestsDto;
import com.example.autoservice.model.dto.response.CarResponseDto;
import com.example.autoservice.service.CarOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarMapper implements RequestMapper<Car, CarRequestsDto>,
        ResponseMapper<Car, CarResponseDto> {
    private final CarOwnerService carOwnerService;

    public Car mapToModel(CarRequestsDto dto) {
        return Car.builder()
                .year(dto.getYear())
                .number(dto.getNumber())
                .mark(dto.getMark())
                .model(dto.getModel())
                .owner(carOwnerService.findById(dto.getCarOwnerId()))
                .build();
    }

    @Override
    public CarResponseDto mapToResponse(Car model) {
        return CarResponseDto.builder()
                .year(model.getYear())
                .id(model.getId())
                .carOwnerId(model.getOwner().getId())
                .number(model.getNumber())
                .mark(model.getMark())
                .model(model.getModel())
                .build();
    }
}
