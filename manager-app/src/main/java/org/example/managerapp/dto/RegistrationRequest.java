package org.example.managerapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = "{user.error.username.null}")
    private String username;

    @NotEmpty(message = "{user.error.email.null}")
    @Email(message = "{user.error.email.invalid}")
    private String email;

    @ToString.Exclude
    @NotEmpty(message = "{user.error.password.null}")
    private String password;

    @ToString.Exclude
    @NotEmpty(message = "{user.error.password.null}")
    private String confirmPassword;

}