package com.example.autoservice.controller;

import com.example.autoservice.mapper.MasterMapper;
import com.example.autoservice.mapper.OrderMapper;
import com.example.autoservice.model.dto.request.MasterRequestDto;
import com.example.autoservice.model.dto.response.MasterResponseDto;
import com.example.autoservice.model.dto.response.OrderResponseDto;
import com.example.autoservice.service.MasterService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masters")
@RequiredArgsConstructor
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper mapper;
    private final OrderMapper orderMapper;

    @PostMapping
    public MasterResponseDto crete(@RequestBody MasterRequestDto masterRequestDto) {
        return mapper.mapToResponse(
                masterService.create(
                        mapper.mapToModel(masterRequestDto)));
    }

    @PutMapping("/update/{masterId}")
    public MasterResponseDto update(@PathVariable Long masterId,
                                    @RequestBody MasterRequestDto masterRequestDto) {
        return mapper.mapToResponse(
                masterService.update(
                        masterId, mapper.mapToModel(masterRequestDto)));

    }

    @GetMapping("/orders/{masterId}")
    public List<OrderResponseDto> getOrders(@PathVariable Long masterId) {
        return masterService.getOrders(masterId)
                .stream()
                .map(orderMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/salary/{masterId}")
    public Double getSalary(@PathVariable Long masterId) {
        return masterService.salary(masterId);
    }
}
