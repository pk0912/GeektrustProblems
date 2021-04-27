package com.ledgerCo.exceptions;

public class EMIDoesNotExistException extends Exception {
    public EMIDoesNotExistException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
