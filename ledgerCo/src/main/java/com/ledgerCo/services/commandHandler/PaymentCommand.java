package com.ledgerCo.services.commandHandler;

import com.ledgerCo.exceptions.EMIDoesNotExistException;
import com.ledgerCo.exceptions.LoanDoesNotExistException;
import com.ledgerCo.exceptions.WrongEntitiesNumberException;
import com.ledgerCo.models.Loan;
import com.ledgerCo.services.BankService;
import com.ledgerCo.services.BorrowerService;
import com.ledgerCo.services.LoanService;


public class PaymentCommand implements Command {
    private final LoanService loanService;
    private final BankService bankService;
    private final BorrowerService borrowerService;

    public PaymentCommand(LoanService loanService, BankService bankService, BorrowerService borrowerService) {
        this.loanService = loanService;
        this.bankService = bankService;
        this.borrowerService = borrowerService;
    }

    @Override
    public void execute(String[] units) throws EMIDoesNotExistException, LoanDoesNotExistException, WrongEntitiesNumberException {
        if (units.length == 5) {
            String loanKey = this.bankService.getBank(units[1]) + "-" + this.borrowerService.getBorrower(units[2]).getName();
            Loan loan = loanService.getLoanDetails(loanKey);
            double lumpsumAmount = Double.parseDouble(units[3]);
            int emiNumber = Integer.parseInt(units[4]);
            if (loan != null) {
                if (emiNumber <= loan.getEmis().size()) {
                    loanService.addLumpsumPayment(loan, lumpsumAmount, emiNumber);
                } else {
                    throw new EMIDoesNotExistException("EMI Does not exist error.");
                }
            } else {
                throw new LoanDoesNotExistException("Loan does not exist error.");
            }
        }
        else {
            throw new WrongEntitiesNumberException("Wrong number of entities passed in PAYMENT command.");
        }
    }
}
