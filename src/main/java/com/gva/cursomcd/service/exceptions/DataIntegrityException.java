package com.gva.cursomcd.service.exceptions;

public class DataIntegrityException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DataIntegrityException() {
    }

    public DataIntegrityException(String message) {
        super(message);
    }
}