package com.softserve.academy.Tips4Trips.controller.exception;

import com.softserve.academy.Tips4Trips.dto.exception.ExceptionDTO;
import com.softserve.academy.Tips4Trips.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler (value = { DataNotFoundException.class,
            UpdateUnexistingException.class, DeleteUnexistingException.class,
            UsernameNotFoundException.class})
    public final ResponseEntity<ExceptionDTO> handleNotFoundException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));

        if (exception instanceof UsernameNotFoundException) {
            Throwable cause = exception.getCause();
            if (cause != null) {
                errorDetails.setCause(cause.getMessage());
            }
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (value = { InvalidDataException.class,
            UpdateException.class, DeleteException.class })
    public final ResponseEntity<ExceptionDTO> handleBadRequestException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (value = { AuthenticationException.class })
    public final ResponseEntity<ExceptionDTO> handleUnauthorisedException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler (value = { Exception.class })
    public final ResponseEntity<ExceptionDTO> handleInternalServerException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public final ResponseEntity<ExceptionDTO> handleMethodArgumentNotValidException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public final ResponseEntity<ExceptionDTO> handleHttpRequestMethodNotSupportedException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public final ResponseEntity<ExceptionDTO> handleHttpMediaTypeNotSupportedException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}