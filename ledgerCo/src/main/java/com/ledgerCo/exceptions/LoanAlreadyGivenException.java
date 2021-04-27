package com.ledgerCo.exceptions;

public class LoanAlreadyGivenException extends Exception {
    public LoanAlreadyGivenException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
