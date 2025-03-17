package org.example.catalogueservice.service;

import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TaskService {

    Iterable<Task> findAll();

    Optional<Task> findTaskById(int taskId);

    void deleteTaskById(Integer taskId);

    Task createTask(String title, String description, TaskStatus status, LocalDateTime deadline);

    void updateTask(Integer id, String title, String description, TaskStatus status, LocalDateTime deadline);

}