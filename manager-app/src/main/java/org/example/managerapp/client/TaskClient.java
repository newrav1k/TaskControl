package org.example.managerapp.client;

import org.example.managerapp.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskClient {

    List<Task> findAll();

    List<Task> findAllByUserId(Long userId);

    Optional<Task> findById(Integer taskId);

    void deleteById(Integer taskId, Long userId);

    Task createTask(String title, String description, String status, LocalDateTime deadline, Long userId);

    void updateTask(Integer id, String title, String description, String status, LocalDateTime deadline);

}