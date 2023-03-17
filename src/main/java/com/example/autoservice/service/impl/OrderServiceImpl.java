package com.example.autoservice.service.impl;

import com.example.autoservice.model.Order;
import com.example.autoservice.model.Status;
import com.example.autoservice.repository.OrderRepository;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final ProductService productService;

    @Override
    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can`t find order by id: " + id));
    }

    @Override
    public Order create(Order orderRequestDto) {
        return repository.save(orderRequestDto);
    }

    @Override
    public List<Order> findByIdIn(List<Long> ordersId) {
        return repository.findByIdIn(ordersId);
    }

    @Override
    public Order addProduct(Long orderId, Long productId) {
        Order existedOrder = findById(orderId);
        existedOrder.getProducts().add(productService.findById(productId));
        return repository.save(existedOrder);
    }

    @Override
    public Order update(Long orderId, Order orderRequestDto) {
        Order existedOrder = findById(orderId);
        existedOrder.setCar(orderRequestDto.getCar());
        existedOrder.setOrderStatus(orderRequestDto.getOrderStatus());
        existedOrder.setPrice(orderRequestDto.getPrice());
        existedOrder.setProducts(orderRequestDto.getProducts());
        existedOrder.setDateCreated(orderRequestDto.getDateCreated());
        existedOrder.setDescription(orderRequestDto.getDescription());
        existedOrder.setDateCompleted(orderRequestDto.getDateCreated());
        existedOrder.setTasks(orderRequestDto.getTasks());
        return repository.save(existedOrder);
    }

    @Override
    public Order changeStatus(Long orderId, String status) {
        Order existed = findById(orderId);
        existed.setOrderStatus(Status.valueOf(status));
        return repository.save(existed);
    }

    @Override
    public Double getPrice(Long orderId) {
        Order existedOrder = findById(orderId);
        return existedOrder.getPrice();
    }
}
