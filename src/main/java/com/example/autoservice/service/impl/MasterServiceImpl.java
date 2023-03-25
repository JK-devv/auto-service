package com.example.autoservice.service.impl;

import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Task;
import com.example.autoservice.repository.MasterRepository;
import com.example.autoservice.service.MasterService;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private static final int MASTERS_PERCENT = 40;
    private final MasterRepository repository;

    @Override
    public Master create(Master masterRequest) {
        return repository.save(masterRequest);
    }

    @Override
    public Master update(Long masterId, Master masterRequest) {
        Master existedMaster = repository.findById(masterId)
                .orElseThrow(
                        () -> new RuntimeException("Can`t find master by id: " + masterId));
        existedMaster.setName(masterRequest.getName());
        existedMaster.setOrders(masterRequest.getOrders());
        return repository.save(existedMaster);
    }

    @Override
    public List<Order> getOrders(Long masterId) {
        return repository.findByIdWithOrders(masterId);
    }

    @Override
    public Double salary(Long masterId) {
        Master masterById = repository.findByIdWithOrdersAndTasks(masterId)
                .orElseThrow(() -> new RuntimeException("Can't find master by id: " + masterId));
        double favorsPrice = masterById.getOrders().stream()
                .flatMap(o -> o.getTasks().stream())
                .map(Task::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        return (favorsPrice * MASTERS_PERCENT) / 100;
    }

    @Override
    public Master findByIdWithOrdersAndFavors(Long masterId) {
        return repository.findByIdWithOrdersAndTasks(masterId)
                .orElseThrow(() -> new RuntimeException(
                        "Can`t find master by id: " + masterId));
    }

    @Override
    public Master findById(Long masterId) {
        return repository.findById(masterId)
                .orElseThrow(() -> new RuntimeException(
                        "Can`t find master by id: " + masterId));
    }
}


