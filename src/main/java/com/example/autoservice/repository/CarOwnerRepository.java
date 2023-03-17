package com.example.autoservice.repository;

import com.example.autoservice.model.CarOwner;
import com.example.autoservice.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {
    @Query("SELECT c FROM CarOwner c JOIN FETCH c.orders o WHERE c.id = :carOwnerId")
    List<Order> getOrdersByCarOwnerId(@Param("carOwnerId") Long carOwnerId);

    @Query("SELECT c FROM CarOwner c JOIN FETCH c.orders o"
            + " JOIN FETCH o.tasks JOIN FETCH o.products WHERE c.id = :carOwnerId")
    List<Order> getOrdersWithTasksAndProductByCarOwnerId(@Param("carOwnerId") Long carOwnerId);
}
