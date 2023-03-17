package com.example.autoservice.controller;

import com.example.autoservice.mapper.TaskMapper;
import com.example.autoservice.model.dto.request.TaskRequestDto;
import com.example.autoservice.model.dto.response.TaskResponseDto;
import com.example.autoservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper mapper;

    @GetMapping("/create")
    public TaskResponseDto create(@RequestBody TaskRequestDto taskRequestDto) {
        return mapper.mapToResponse(taskService.create(mapper.mapToModel(taskRequestDto)));
    }

    @PutMapping("/update/{taskId}")
    public TaskResponseDto update(@PathVariable Long taskId,
                                  @RequestBody TaskRequestDto taskRequestDto) {
        return mapper.mapToResponse(
                taskService.update(
                        taskId, mapper.mapToModel(taskRequestDto)));
    }

    @PatchMapping("/update_status/{taskId}")
    public TaskResponseDto changeStatus(@PathVariable Long taskId,
                                        @RequestParam String status) {
        return mapper.mapToResponse(taskService.changeStatus(taskId, status));
    }
}
