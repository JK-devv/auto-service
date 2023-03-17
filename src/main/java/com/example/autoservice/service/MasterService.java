package com.example.autoservice.service;

import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import java.util.List;

public interface MasterService {

    Master create(Master masterRequest);

    Master update(Long masterId, Master masterRequest);

    List<Order> getOrders(Long masterId);

    Double salary(Long masterId);

    Master findByIdWithOrdersAndFavors(Long masterId);

    Master findById(Long masterId);
}
