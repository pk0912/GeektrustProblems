package com.ledgerCo.exceptions;

public class WrongCommandException extends Exception{
    public WrongCommandException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
