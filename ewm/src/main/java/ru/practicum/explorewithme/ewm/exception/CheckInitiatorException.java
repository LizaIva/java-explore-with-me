package ru.practicum.explorewithme.ewm.exception;

public class CheckInitiatorException extends RuntimeException {
    private final String message;

    public CheckInitiatorException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
