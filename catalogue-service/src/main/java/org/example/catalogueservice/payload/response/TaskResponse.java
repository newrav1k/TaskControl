package org.example.catalogueservice.payload.response;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record TaskResponse(
        Integer id,
        @NotBlank(message = "{payload.title.error.blank}") @Size(max = 50, message = "{payload.title.error.size}") String title,
        @NotBlank(message = "{payload.description.error.blank}") @Size(max = 250, message = "{payload.description.error.size}") String description,
        @NotNull(message = "{payload.status.error.null}") String status,
        @NotNull(message = "{payload.deadline.error.null}") @FutureOrPresent(message = "{payload.deadline.error.future}") LocalDateTime deadline
) {

}