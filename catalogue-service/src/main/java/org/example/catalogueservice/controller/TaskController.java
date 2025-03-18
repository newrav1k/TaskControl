package org.example.catalogueservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.payload.NewTaskPayload;
import org.example.catalogueservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task-api/tasks/{taskId:\\d+}")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @ModelAttribute("task")
    public Task task(@PathVariable Integer taskId) {
        return this.taskService.findTaskById(taskId)
                .orElseThrow(() -> new NoSuchElementException("tasks.not.found"));
    }

    @GetMapping
    public Task findTask(@ModelAttribute("task") Task task) {
        return task;
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Integer taskId,
                                           @RequestBody NewTaskPayload payload) {
        this.taskService.updateTask(taskId, payload.title(),
                payload.description(), payload.status(), payload.deadline());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Integer taskId) {
        this.taskService.deleteTaskById(taskId);
        return ResponseEntity.noContent().build();
    }

}