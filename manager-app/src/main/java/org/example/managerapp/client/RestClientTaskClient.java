package org.example.managerapp.client;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.entity.Task;
import org.example.managerapp.entity.TaskStatus;
import org.example.managerapp.payload.NewTaskPayload;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestClientTaskClient implements TaskClient {

    private static final ParameterizedTypeReference<List<Task>> PARAMETERIZED_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final RestClient restClient;

    @Override
    public List<Task> findAll() {
        return this.restClient.get()
                .uri("/task-api/tasks/list")
                .retrieve()
                .body(PARAMETERIZED_TYPE_REFERENCE);
    }

    @Override
    public Optional<Task> findById(int taskId) {
        return Optional.ofNullable(this.restClient.get()
                .uri("/task-api/tasks/{taskId}", taskId)
                .retrieve()
                .body(Task.class));
    }

    @Override
    public void deleteById(Integer taskId) {
        this.restClient.delete()
                .uri("/task-api/tasks/{taskId}/delete", taskId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public Task createTask(String title, String description, String status, LocalDateTime deadline) {
        return this.restClient.post()
                .uri("/task-api/tasks/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new NewTaskPayload(title, description, status, deadline))
                .retrieve()
                .body(Task.class);
    }

    @Override
    public void updateTask(Integer taskId, String title, String description, String status, LocalDateTime deadline) {
        this.restClient.post()
                .uri("/task-api/tasks/{taskId}/update", taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new NewTaskPayload(title, description, status, deadline))
                .retrieve()
                .toBodilessEntity();
    }

}