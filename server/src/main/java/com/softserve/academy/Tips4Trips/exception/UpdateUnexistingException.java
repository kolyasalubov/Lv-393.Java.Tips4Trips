package com.softserve.academy.Tips4Trips.exception;

public class UpdateUnexistingException extends UpdateException {
    public UpdateUnexistingException() {}
    public UpdateUnexistingException(String message) {
        super(message);
    }
}
