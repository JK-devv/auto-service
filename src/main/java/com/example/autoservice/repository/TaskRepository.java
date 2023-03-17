package com.example.autoservice.repository;

import com.example.autoservice.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIdIn(List<Long> favorsId);
}
