package com.example.autoservice.mapper;

import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.model.Status;
import com.example.autoservice.model.Task;
import com.example.autoservice.model.dto.request.OrderRequestDto;
import com.example.autoservice.model.dto.response.OrderResponseDto;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.ProductService;
import com.example.autoservice.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper implements RequestMapper<Order, OrderRequestDto>,
        ResponseMapper<Order, OrderResponseDto> {
    private final CarService carService;
    private final ProductService productService;
    private final TaskService taskService;

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        List<Task> tasksById = taskService.findByIdIn(dto.getTasksId());
        List<Product> productsById = productService.findByIdIn(dto.getProductsId());
        Double price = DoubleStream.concat(
                tasksById.stream().mapToDouble(Task::getPrice),
                productsById.stream().mapToDouble(Product::getPrice)
        ).sum();

        return Order.builder()
                .car(carService.findById(dto.getCarId()))
                .orderStatus(Status.valueOf(dto.getStatus()))
                .description(dto.getDescription())
                .products(productsById)
                .dateCreated(dto.getDateCreated())
                .dateCompleted(dto.getDateCompleted())
                .tasks(tasksById)
                .price(price)
                .build();
    }

    @Override
    public OrderResponseDto mapToResponse(Order model) {
        return OrderResponseDto.builder()
                .tasksId(model.getTasks()
                        .stream()
                        .map(Task::getId)
                        .collect(Collectors.toList()))
                .carId(model.getCar().getId())
                .id(model.getId())
                .status(model.getOrderStatus().toString())
                .productsId(model.getProducts()
                        .stream()
                        .map(Product::getId)
                        .collect(Collectors.toList()))
                .dateCompleted(model.getDateCompleted())
                .dateCreated(model.getDateCreated())
                .price(model.getPrice())
                .build();
    }
}
