package com.softserve.academy.Tips4Trips.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {}
    public InvalidDataException(String message) {
        super(message);
    }
}
