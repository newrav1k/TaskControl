package org.example.managerapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.managerapp.dto.RegistrationRequest;
import org.example.managerapp.service.Newrav1kUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final Newrav1kUserService userService;

    private final AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String registrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute RegistrationRequest request,
                               BindingResult bindingResult) throws BindException {
        log.info("Received registration request: {}", request);
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            String username = request.getUsername();
            String email = request.getEmail();
            String password = request.getPassword();
            String confirmPassword = request.getConfirmPassword();
            if (password.equals(confirmPassword)) {
                this.userService.createUser(username, email, password);

                Authentication authenticate = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password));
                SecurityContextHolder.getContext().setAuthentication(authenticate);

                return "redirect:/tasks/list";
            } else {
                bindingResult.rejectValue("password", "Passwords do not match");
                throw new BindException(bindingResult);
            }
        }
    }

}