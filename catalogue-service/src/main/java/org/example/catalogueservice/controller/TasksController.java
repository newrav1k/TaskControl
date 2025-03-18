package org.example.catalogueservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.payload.NewTaskPayload;
import org.example.catalogueservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/task-api/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;

    @GetMapping("/list")
    public Iterable<Task> findAllTasks() {
        return this.taskService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody NewTaskPayload payload, UriComponentsBuilder uriBuilder) {
        Task task = this.taskService.createTask(payload.title(), payload.description(), payload.status(), payload.deadline());
        return ResponseEntity.created(uriBuilder
                        .path("/task-api/tasks/{taskId}")
                        .build(Map.of("taskId", task.getId())))
                .build();
    }

}