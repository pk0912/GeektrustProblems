package com.ledgerCo.services.commandHandler;

import com.ledgerCo.exceptions.EMIDoesNotExistException;
import com.ledgerCo.exceptions.LoanAlreadyGivenException;
import com.ledgerCo.exceptions.LoanDoesNotExistException;
import com.ledgerCo.exceptions.WrongEntitiesNumberException;

public interface Command {
    void execute(String[] units) throws LoanAlreadyGivenException, LoanDoesNotExistException, EMIDoesNotExistException, WrongEntitiesNumberException;
}
