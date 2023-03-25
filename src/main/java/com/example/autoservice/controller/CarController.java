package com.example.autoservice.controller;

import com.example.autoservice.mapper.CarMapper;
import com.example.autoservice.model.dto.request.CarRequestsDto;
import com.example.autoservice.model.dto.response.CarResponseDto;
import com.example.autoservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarMapper mapper;

    @PostMapping
    public CarResponseDto create(@RequestBody CarRequestsDto carRequestsDto) {
        return mapper.mapToResponse(
                carService.create(
                        mapper.mapToModel(carRequestsDto)));
    }

    @PutMapping("/update/{carId}")
    public CarResponseDto update(@PathVariable Long carId,
                                 @RequestBody CarRequestsDto carRequestsDto) {
        return mapper.mapToResponse(
                carService.update(carId,
                        mapper.mapToModel(carRequestsDto)));
    }
}
