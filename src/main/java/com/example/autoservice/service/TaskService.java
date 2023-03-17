package com.example.autoservice.service;

import com.example.autoservice.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> findByIdIn(List<Long> favorsId);

    Task create(Task taskRequestDto);

    Task update(Long taskId, Task taskRequestDto);

    Task changeStatus(Long taskId, String newStatus);
}
