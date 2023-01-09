package com.example.ticketselling.exception;

public class DuplicateDestinationException extends RuntimeException {
    public DuplicateDestinationException() {
        super("A destination with the same name already exists.");
    }
}
