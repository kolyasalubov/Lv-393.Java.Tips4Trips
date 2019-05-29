package com.softserve.academy.Tips4Trips.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {}
    public DataNotFoundException(String message) {
        super(message);
    }
}
