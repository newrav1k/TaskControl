package org.example.managerapp.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskPayload {

    @NotBlank(message = "{payload.title.error.blank}")
    @Size(max = 50, message = "{payload.title.error.size}")
    private String title;

    @NotBlank(message = "{payload.description.error.blank}")
    @Size(max = 250, message = "{payload.description.error.size}")
    private String description;

    @NotNull(message = "{payload.status.error.null}")
    private String status;

    @NotNull(message = "{payload.deadline.error.null}")
    @FutureOrPresent(message = "{payload.deadline.error.future}")
    private LocalDateTime deadline;

}