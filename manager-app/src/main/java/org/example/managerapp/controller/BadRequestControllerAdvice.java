package org.example.managerapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class BadRequestControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException exception, Model model, Locale locale) {
        model.addAttribute("errorCode", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("errorMessage", exception.getBindingResult().getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage).filter(Objects::nonNull)
                .map(error -> this.messageSource.getMessage(error, new Object[0], error, locale))
                .toList());
        return "errors/error";
    }

}