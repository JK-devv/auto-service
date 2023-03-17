package com.example.autoservice.controller;

import com.example.autoservice.mapper.OrderMapper;
import com.example.autoservice.model.dto.request.OrderRequestDto;
import com.example.autoservice.model.dto.response.OrderResponseDto;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final TaskService taskService;

    @PostMapping("/create")
    public OrderResponseDto create(@RequestBody OrderRequestDto orderRequestDto) {
        return mapper.mapToResponse(orderService.create(mapper.mapToModel(orderRequestDto)));
    }

    @PatchMapping("/add_product/{orderId}/{productId}")
    public OrderResponseDto addProduct(@PathVariable Long orderId,
                                       @PathVariable Long productId) {
        return mapper.mapToResponse(orderService.addProduct(orderId, productId));
    }

    @PutMapping("/update/{orderId}")
    public OrderResponseDto update(@PathVariable Long orderId,
                                   @RequestBody OrderRequestDto orderRequestDto) {
        return mapper.mapToResponse(
                orderService.update(
                        orderId, mapper.mapToModel(orderRequestDto)));
    }

    @PatchMapping("/update_status/{orderId}")
    public OrderResponseDto updateStatus(@PathVariable Long orderId,
                                         @RequestParam String status) {
        return mapper.mapToResponse(orderService.changeStatus(orderId, status));
    }

    @GetMapping("/total_price/{orderId}")
    public Double totalPrice(@PathVariable Long orderId) {
        return orderService.getPrice(orderId);
    }

}
