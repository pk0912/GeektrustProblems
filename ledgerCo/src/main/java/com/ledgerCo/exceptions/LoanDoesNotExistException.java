package com.ledgerCo.exceptions;

public class LoanDoesNotExistException extends Exception {
    public LoanDoesNotExistException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
