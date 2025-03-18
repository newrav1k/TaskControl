package org.example.catalogueservice.service;

import lombok.RequiredArgsConstructor;
import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.entity.TaskStatus;
import org.example.catalogueservice.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Iterable<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Optional<Task> findTaskById(int taskId) {
        return this.taskRepository.findById(taskId);
    }

    @Override
    @Transactional
    public void deleteTaskById(Integer taskId) {
        this.taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public Task createTask(String title, String description, TaskStatus status, LocalDateTime deadline) {
        return this.taskRepository.save(new Task(null, title, description, status, deadline));
    }

    @Override
    @Transactional
    public void updateTask(Integer id, String title, String description, TaskStatus status, LocalDateTime deadline) {
        this.taskRepository.findById(id).ifPresent(task -> {
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            task.setDeadline(deadline);
        });
    }

}