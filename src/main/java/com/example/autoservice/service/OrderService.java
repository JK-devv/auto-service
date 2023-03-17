package com.example.autoservice.service;

import com.example.autoservice.model.Order;
import java.util.List;

public interface OrderService {
    Order findById(Long id);

    Order create(Order orderRequestDto);

    List<Order> findByIdIn(List<Long> ordersId);

    Order addProduct(Long orderId, Long productId);

    Order update(Long orderId, Order orderRequestDto);

    Order changeStatus(Long orderId, String status);

    Double getPrice(Long orderId);
}
