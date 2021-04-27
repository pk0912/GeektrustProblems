package com.ledgerCo.services.commandHandler;

import com.ledgerCo.exceptions.WrongCommandException;
import com.ledgerCo.services.BankService;
import com.ledgerCo.services.BorrowerService;
import com.ledgerCo.services.LoanService;

public class CommandFactory {
    public static Command getCommand(String name, LoanService loanService, BankService bankService, BorrowerService borrowerService) throws WrongCommandException {
        Command command = null;
        switch (name) {
            case "loan":
                command = new LoanCommand(loanService, bankService, borrowerService);
                break;
            case "balance":
                command = new BalanceCommand(loanService, bankService, borrowerService);
                break;
            case "payment":
                command = new PaymentCommand(loanService, bankService, borrowerService);
                break;
            default:
                throw new WrongCommandException("Wrong command entered. Valid options are loan/balance/payment");
        }
        return command;
    }
}
