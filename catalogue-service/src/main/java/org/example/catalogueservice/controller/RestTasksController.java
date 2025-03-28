package org.example.catalogueservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.payload.NewTaskPayload;
import org.example.catalogueservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
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
public class RestTasksController {

    private final TaskService taskService;

    @GetMapping("/list")
    public Iterable<Task> findAllTasks() {
        return this.taskService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody NewTaskPayload payload,
                                        BindingResult bindingResult, UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Task task = this.taskService.createTask(payload.title(), payload.description(), payload.status(), payload.deadline());
            return ResponseEntity.created(uriBuilder
                            .path("/task-api/tasks/{taskId}")
                            .build(Map.of("taskId", task.getId())))
                    .build();
        }
    }

}