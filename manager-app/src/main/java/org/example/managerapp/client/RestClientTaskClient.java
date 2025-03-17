package org.example.managerapp.client;

import org.example.managerapp.entity.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestClientTaskClient implements ProductClient {

    private RestClient restClient;

    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
    public Optional<Task> findById(int taskId) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer taskId) {

    }

    @Override
    public Task createTask(String title, String description, String status, LocalDateTime deadline) {
        return null;
    }

    @Override
    public void updateTask(Integer id, String title, String description, String status, LocalDateTime deadline) {

    }

}