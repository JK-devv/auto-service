package com.example.autoservice.mapper;

import com.example.autoservice.model.Status;
import com.example.autoservice.model.Task;
import com.example.autoservice.model.dto.request.TaskRequestDto;
import com.example.autoservice.model.dto.response.TaskResponseDto;
import com.example.autoservice.service.MasterService;
import com.example.autoservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskMapper implements RequestMapper<Task, TaskRequestDto>,
        ResponseMapper<Task, TaskResponseDto> {
    private final OrderService orderService;
    private final MasterService masterService;

    @Override
    public Task mapToModel(TaskRequestDto dto) {
        return Task.builder()
                .price(dto.getPrice())
                .order(orderService.findById(dto.getOrderId()))
                .master(masterService.findById(dto.getMasterId()))
                .orderStatus(Status.valueOf(dto.getStatus()))
                .build();
    }

    @Override
    public TaskResponseDto mapToResponse(Task model) {
        return TaskResponseDto.builder()
                .id(model.getId())
                .masterId(model.getId())
                .orderId(model.getOrder().getId())
                .status(model.getOrderStatus().toString())
                .price(model.getPrice())
                .build();
    }
}
