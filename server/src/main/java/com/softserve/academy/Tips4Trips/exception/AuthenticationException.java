package com.softserve.academy.Tips4Trips.exception;

public class AuthenticationException extends Exception {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
