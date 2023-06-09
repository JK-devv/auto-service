package com.example.autoservice.repository;

import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
    @Query("SELECT m FROM Master m JOIN FETCH m.orders o JOIN FETCH o.tasks WHERE m.id = :masterId")
    Optional<Master> findByIdWithOrdersAndTasks(@Param("masterId") Long masterId);

    @Query("SELECT m FROM Master m JOIN FETCH m.orders WHERE m.id = :masterId")
    List<Order> findByIdWithOrders(@Param("masterId") Long masterId);
}
