package org.example.managerapp.service;

import org.example.managerapp.entity.Task;
import org.example.managerapp.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> findAll();

    Optional<Task> findTaskById(int taskId);

    void deleteTaskById(Integer taskId);

    Task createTask(String title, String description, TaskStatus status, LocalDateTime deadline);

    void updateTask(Integer id, String title, String description, TaskStatus status, LocalDateTime deadline);

}