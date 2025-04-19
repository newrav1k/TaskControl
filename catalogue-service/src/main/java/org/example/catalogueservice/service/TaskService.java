package org.example.catalogueservice.service;

import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TaskService {

    Iterable<Task> findAll();

    Iterable<Task> findAllByUserId(Long userId);

    Optional<Task> findTaskById(Integer taskId);

    void deleteTaskById(Integer taskId);

    Task createTask(String title, String description, TaskStatus status, LocalDateTime deadline, Long userId);

    void updateTask(Integer taskId, String title, String description, TaskStatus status, LocalDateTime deadline);

}