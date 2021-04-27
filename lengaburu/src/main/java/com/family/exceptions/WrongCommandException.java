package com.family.exceptions;

public class WrongCommandException extends Exception {
    public WrongCommandException(String msg) {
        super(msg);
    }
}
