package org.example.managerapp.client;

import org.example.managerapp.entity.Task;
import org.example.managerapp.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductClient {

    List<Task> findAll();

    Optional<Task> findById(int taskId);

    void deleteById(Integer taskId);

    Task createTask(String title, String description, String status, LocalDateTime deadline);

    void updateTask(Integer id, String title, String description, String status, LocalDateTime deadline);

}