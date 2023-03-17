package com.example.autoservice.service.impl;

import com.example.autoservice.model.Status;
import com.example.autoservice.model.Task;
import com.example.autoservice.repository.TaskRepository;
import com.example.autoservice.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Override
    public List<Task> findByIdIn(List<Long> favorsId) {
        return repository.findByIdIn(favorsId);
    }

    @Override
    public Task create(Task taskRequestDto) {
        return repository.save(taskRequestDto);
    }

    @Override
    public Task update(Long taskId, Task taskRequestDto) {
        Task existedTask = repository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Can`t find favor by id: " + taskId));
        existedTask.setOrderStatus(taskRequestDto.getOrderStatus());
        existedTask.setPrice(taskRequestDto.getPrice());
        existedTask.setMaster(taskRequestDto.getMaster());
        existedTask.setOrder(taskRequestDto.getOrder());
        return repository.save(existedTask);
    }

    @Override
    public Task changeStatus(Long taskId, String newStatus) {
        Task existedTask = repository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Can`t find favor by id: " + taskId));
        existedTask.setOrderStatus(Status.valueOf(newStatus));
        return repository.save(existedTask);
    }
}
