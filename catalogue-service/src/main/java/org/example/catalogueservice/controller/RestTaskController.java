package org.example.catalogueservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.catalogueservice.entity.Task;
import org.example.catalogueservice.payload.NewTaskPayload;
import org.example.catalogueservice.service.TaskService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task-api/tasks/{taskId:\\d+}")
@RequiredArgsConstructor
public class RestTaskController {

    private final TaskService taskService;

    private final MessageSource messageSource;

    @ModelAttribute("task")
    public Task task(@PathVariable Integer taskId) {
        return this.taskService.findTaskById(taskId)
                .orElseThrow(() -> new NoSuchElementException("task.not.found"));
    }

    @GetMapping
    public Task findTask(@ModelAttribute("task") Task task) {
        return task;
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Integer taskId,
                                           @Valid @RequestBody NewTaskPayload payload,
                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.taskService.updateTask(taskId, payload.title(),
                    payload.description(), payload.status(), payload.deadline());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Integer taskId) {
        this.taskService.deleteTaskById(taskId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception, Locale locale) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                this.messageSource.getMessage(exception.getMessage(), new Object[0], exception.getMessage(), locale));
        return ResponseEntity.of(problemDetail).build();
    }

}