package com.example.autoservice.service;

import com.example.autoservice.model.CarOwner;
import com.example.autoservice.model.Order;
import java.util.List;

public interface CarOwnerService {
    CarOwner create(CarOwner carOwnerRequest);

    CarOwner findById(Long id);

    List<Order> getOrders(Long carOwnerId);

    CarOwner update(Long carOwnerId, CarOwner carOwnerRequest);

    Double getTotalPrice(Long carOwnerId);

}
