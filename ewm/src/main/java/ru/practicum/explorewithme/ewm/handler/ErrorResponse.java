package ru.practicum.explorewithme.ewm.handler;

public class ErrorResponse {

    private int status;
    private String error;

    public ErrorResponse(int status) {
        this.status = status;
    }

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }
}
