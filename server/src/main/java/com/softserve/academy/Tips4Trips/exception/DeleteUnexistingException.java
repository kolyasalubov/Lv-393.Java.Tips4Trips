package com.softserve.academy.Tips4Trips.exception;

public class DeleteUnexistingException extends DeleteException {
    public DeleteUnexistingException() {
    }

    public DeleteUnexistingException(String message) {
        super(message);
    }
}