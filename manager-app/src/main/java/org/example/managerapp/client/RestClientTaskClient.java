package org.example.managerapp.client;

import lombok.RequiredArgsConstructor;
import org.example.managerapp.entity.Task;
import org.example.managerapp.exception.BadRequestException;
import org.example.managerapp.payload.NewTaskPayload;
import org.example.managerapp.payload.UpdateTaskPayload;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
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
                .uri("/task-api/tasks")
                .retrieve()
                .body(PARAMETERIZED_TYPE_REFERENCE);
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        return this.restClient.get()
                .uri("/task-api/tasks/list?userId={userId}", userId)
                .retrieve()
                .body(PARAMETERIZED_TYPE_REFERENCE);
    }

    @Override
    public Optional<Task> findById(Integer taskId) {
        try {
            return Optional.ofNullable(this.restClient.get()
                    .uri("/task-api/tasks/{taskId}", taskId)
                    .retrieve()
                    .body(Task.class));
        } catch (HttpClientErrorException.NotFound exception) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Integer taskId, Long userId) {
        this.restClient.delete()
                .uri("/task-api/tasks/{taskId}/delete", taskId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public Task createTask(String title, String description,
                           String status, LocalDateTime deadline, Long userId) {
        try {
            return this.restClient.post()
                    .uri("/task-api/tasks/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NewTaskPayload(title, description, status, deadline, userId))
                    .retrieve()
                    .body(Task.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public void updateTask(Integer taskId, String title, String description,
                           String status, LocalDateTime deadline) {
        try {
            this.restClient.post()
                    .uri("/task-api/tasks/{taskId}/update", taskId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateTaskPayload(title, description, status, deadline))
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

}