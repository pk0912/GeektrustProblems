package com.family.exceptions;

public class PersonDoesNotExistException extends Exception {
    public PersonDoesNotExistException(String msg) {
        super(msg);
    }
}
