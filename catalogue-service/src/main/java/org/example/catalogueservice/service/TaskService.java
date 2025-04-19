package org.example.catalogueservice.service;

import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.entity.TaskStatus;
import org.example.catalogueservice.payload.response.TaskResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskResponse> findAll();

    List<TaskResponse> findAllByUserId(Long userId);

    Optional<TaskResponse> findTaskById(Integer taskId);

    void deleteTaskById(Integer taskId);

    Task createTask(String title, String description, TaskStatus status, LocalDateTime deadline, Long userId);

    void updateTask(Integer taskId, String title, String description, TaskStatus status, LocalDateTime deadline);

}