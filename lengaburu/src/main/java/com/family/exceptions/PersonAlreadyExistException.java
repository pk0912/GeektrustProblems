package com.family.exceptions;

public class PersonAlreadyExistException extends Exception {
    public PersonAlreadyExistException(String msg) {
        super(msg);
    }
}
