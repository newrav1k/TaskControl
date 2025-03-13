package org.example.managerapp.service;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.entity.Task;
import org.example.managerapp.entity.TaskStatus;
import org.example.managerapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Optional<Task> findTaskById(int taskId) {
        return this.taskRepository.findById(taskId);
    }

    @Override
    public void deleteTaskById(Integer taskId) {
        this.taskRepository.deleteById(taskId);
    }

    @Override
    public Task createTask(String title, String description, TaskStatus status, LocalDateTime deadline) {
        return this.taskRepository.save(new Task(null, title, description, status, deadline));
    }

    @Override
    public void updateTask(Integer id, String title, String description, TaskStatus status, LocalDateTime deadline) {
        this.taskRepository.updateTaskById(id, title, description, status, deadline);
    }

}