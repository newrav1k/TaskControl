package org.example.managerapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.managerapp.entity.TaskStatus;

import java.time.LocalDateTime;

public record NewTaskPayload(
        @NotNull
        @Size(min = 3, max = 50, message = "{tasks.title.error.size}") String title,
        @NotBlank(message = "{tasks.description.error.null}")
        @Size(max = 500, message = "{tasks.description.error.size}") String description,
        @NotNull TaskStatus status,
        @NotNull LocalDateTime deadline) {

}