package org.example.managerapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskPayload {

    @NotNull
    @Size(min = 3, max = 50, message = "{tasks.title.error.size}")
    private String title;

    @NotBlank(message = "{tasks.description.error.null}")
    @Size(max = 500, message = "{tasks.description.error.size}")
    private String description;

    @NotNull
    private String status;

    @NotNull
    private LocalDateTime deadline;

    private Long userId;

}