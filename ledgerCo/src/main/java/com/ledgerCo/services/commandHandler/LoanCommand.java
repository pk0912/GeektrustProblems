package com.ledgerCo.services.commandHandler;

import com.ledgerCo.exceptions.LoanAlreadyGivenException;
import com.ledgerCo.exceptions.WrongEntitiesNumberException;
import com.ledgerCo.models.Bank;
import com.ledgerCo.models.Borrower;
import com.ledgerCo.services.BankService;
import com.ledgerCo.services.BorrowerService;
import com.ledgerCo.services.LoanService;

public class LoanCommand implements Command {
    private final LoanService loanService;
    private final BankService bankService;
    private final BorrowerService borrowerService;

    public LoanCommand(LoanService loanService, BankService bankService, BorrowerService borrowerService) {
        this.loanService = loanService;
        this.bankService = bankService;
        this.borrowerService = borrowerService;
    }

    @Override
    public void execute(String[] units) throws LoanAlreadyGivenException, WrongEntitiesNumberException {
        if (units.length == 6) {
            Bank bank = bankService.getBank(units[1]);
            Borrower borrower = borrowerService.getBorrower(units[2]);
            double principal = Double.parseDouble(units[3]);
            int noOfYears = Integer.parseInt(units[4]);
            double rateOfInterest = Double.parseDouble(units[5]);
            this.loanService.addNewLoan(bank, borrower, principal, rateOfInterest, noOfYears);
        }
        else {
            throw new WrongEntitiesNumberException("Wrong number of entities passed in LOAN command.");
        }
    }
}
