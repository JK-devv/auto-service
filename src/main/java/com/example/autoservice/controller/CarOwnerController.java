package com.example.autoservice.controller;

import com.example.autoservice.mapper.CarOwnerMapper;
import com.example.autoservice.model.CarOwner;
import com.example.autoservice.model.dto.request.CarOwnerRequestDto;
import com.example.autoservice.model.dto.response.CarOwnerResponseDto;
import com.example.autoservice.service.CarOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car_owners")
@RequiredArgsConstructor
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final CarOwnerMapper mapper;

    @PostMapping("/create")
    public CarOwnerResponseDto create(@RequestBody
                                      CarOwnerRequestDto carOwnerRequestDto) {
        return mapper.mapToResponse(
                carOwnerService.create(
                        mapper.mapToModel(carOwnerRequestDto)));
    }

    @PutMapping("/update/{carOwnerId}")
    public CarOwner update(@PathVariable Long carOwnerId,
                           @RequestBody CarOwnerRequestDto carOwnerRequestDto) {
        return carOwnerService.update(carOwnerId, null);
    }

    @GetMapping("/total_price/{carOwnerId}")
    public Double getTotalPrice(@PathVariable Long carOwnerId) {
        return carOwnerService.getTotalPrice(carOwnerId);
    }
}
