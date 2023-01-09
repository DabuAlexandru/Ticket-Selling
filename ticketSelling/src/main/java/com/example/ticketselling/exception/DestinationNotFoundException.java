package com.example.ticketselling.exception;

public class DestinationNotFoundException extends RuntimeException {
    public DestinationNotFoundException(long id) {
        super("The destination with id " + id + " doesn't exist.");
    }
}
