package com.example.autoservice.mapper;

import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.dto.request.MasterRequestDto;
import com.example.autoservice.model.dto.response.MasterResponseDto;
import com.example.autoservice.service.OrderService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterMapper implements RequestMapper<Master, MasterRequestDto>,
        ResponseMapper<Master, MasterResponseDto> {
    private final OrderService orderService;

    @Override
    public Master mapToModel(MasterRequestDto dto) {
        return Master.builder()
                .orders(orderService.findByIdIn(dto.getOrdersId()))
                .name(dto.getName())
                .build();
    }

    @Override
    public MasterResponseDto mapToResponse(Master model) {
        return MasterResponseDto.builder()
                .id(model.getId())
                .name(model.getName())
                .ordersId(model.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
