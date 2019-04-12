package com.softserve.academy.Tips4Trips.controller.exception;

import com.softserve.academy.Tips4Trips.dto.exception.ExceptionDTO;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.DeleteUnexistingException;
import com.softserve.academy.Tips4Trips.exception.InvalidDataException;
import com.softserve.academy.Tips4Trips.exception.UpdateUnexistingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler (value = { InvalidDataException.class})
    public final ResponseEntity<ExceptionDTO> handleBadRequestException(
            Exception exception, WebRequest request) {
        ExceptionDTO errorDetails = new ExceptionDTO(new Date(),
                exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    private
}