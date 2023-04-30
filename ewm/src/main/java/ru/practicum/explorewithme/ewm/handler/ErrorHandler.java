package ru.practicum.explorewithme.ewm.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.hibernate.PropertyValueException;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.explorewithme.ewm.exception.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUnknownDataException(UnknownDataException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCheckInitiatorException(CheckInitiatorException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleTimeValidationException(TimeValidationException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleStateValidationException(StateValidationException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCheckDuplicateRequestException(CheckDuplicateRequestException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCheckValidationRequestException(CheckValidationRequestException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(Throwable e) {
        log.error("Unknown error", e);
        return new ErrorResponse("Произошла непредвиденная ошибка.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleParameterMissingException(MissingServletRequestParameterException e) {
        log.error("Required request parameter error.", e);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentsNotValidException(MethodArgumentNotValidException e) {
        log.error("Required request parameter error.", e);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(HttpStatus.CONFLICT.value());
    }
}
