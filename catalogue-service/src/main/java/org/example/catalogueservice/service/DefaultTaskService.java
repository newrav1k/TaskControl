package org.example.catalogueservice.service;

import lombok.RequiredArgsConstructor;
import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.entity.TaskStatus;
import org.example.catalogueservice.mapper.TaskMapper;
import org.example.catalogueservice.payload.response.TaskResponse;
import org.example.catalogueservice.repository.TaskRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultTaskService implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponse> findAll() {
        return this.taskRepository.findAll()
                .stream()
                .map(this.taskMapper::toTaskResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> findAllByUserId(Long userId) {
        return this.taskRepository.findAllByUserId(userId)
                .stream()
                .map(this.taskMapper::toTaskResponse)
                .toList();
    }

    @Override
    @Cacheable(value = "task", key = "#taskId")
    public Optional<TaskResponse> findTaskById(Integer taskId) {
        return this.taskRepository.findById(taskId)
                .map(this.taskMapper::toTaskResponse);
    }

    @Override
    @CacheEvict(value = "task", key = "#taskId")
    @Transactional
    public void deleteTaskById(Integer taskId) {
        this.taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public Task createTask(String title, String description, TaskStatus status, LocalDateTime deadline, Long userId) {
        return this.taskRepository.save(new Task(null, title, description, status, deadline, userId));
    }

    @Override
    @CachePut(value = "task", key = "#taskId")
    @Transactional
    public void updateTask(Integer taskId, String title, String description,
                           TaskStatus status, LocalDateTime deadline) {
        this.taskRepository.findById(taskId).ifPresent(task -> {
            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            task.setDeadline(deadline);
        });
    }

}