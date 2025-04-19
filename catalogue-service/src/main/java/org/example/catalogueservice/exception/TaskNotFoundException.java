package org.example.catalogueservice.exception;

import java.io.Serial;

public class TaskNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1244251897490785387L;

    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TaskNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}