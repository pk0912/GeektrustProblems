package com.ledgerCo.services.commandHandler;

import com.ledgerCo.exceptions.EMIDoesNotExistException;
import com.ledgerCo.exceptions.LoanDoesNotExistException;
import com.ledgerCo.exceptions.WrongEntitiesNumberException;
import com.ledgerCo.models.Loan;
import com.ledgerCo.services.BankService;
import com.ledgerCo.services.BorrowerService;
import com.ledgerCo.services.LoanService;

public class BalanceCommand implements Command {
    private final LoanService loanService;
    private final BankService bankService;
    private final BorrowerService borrowerService;

    public BalanceCommand(LoanService loanService, BankService bankService, BorrowerService borrowerService) {
        this.loanService = loanService;
        this.bankService = bankService;
        this.borrowerService = borrowerService;
    }

    @Override
    public void execute(String[] units) throws LoanDoesNotExistException, EMIDoesNotExistException, WrongEntitiesNumberException {
        if (units.length == 4) {
            String loanKey = this.bankService.getBank(units[1]) + "-" + this.borrowerService.getBorrower(units[2]).getName();
            Loan loan = loanService.getLoanDetails(loanKey);
            int emiNumber = Integer.parseInt(units[3]);
            if (loan != null) {
                if (emiNumber <= loan.getEmis().size()) {
                    System.out.println(loanService.getBalance(loan, emiNumber));
                } else {
                    throw new EMIDoesNotExistException("EMI Does not exist error.");
                }
            } else {
                throw new LoanDoesNotExistException("Loan does not exist error.");
            }
        }
        else {
            throw new WrongEntitiesNumberException("Wrong number of entities passed in BALANCE command.");
        }
    }
}
