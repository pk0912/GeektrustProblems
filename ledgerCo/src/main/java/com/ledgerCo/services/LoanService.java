package com.ledgerCo.services;

import com.ledgerCo.dao.LoanDao;
import com.ledgerCo.exceptions.LoanAlreadyGivenException;
import com.ledgerCo.exceptions.LoanDoesNotExistException;
import com.ledgerCo.models.Bank;
import com.ledgerCo.models.Borrower;
import com.ledgerCo.models.EMI;
import com.ledgerCo.models.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private final LoanDao loanDao;

    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    public void addNewLoan(Bank bank, Borrower borrower, double principal, double rateOfInterest, int noOfYears) throws LoanAlreadyGivenException {
        // Creates a new Loan. Throws Exception if there is already a loan given to the borrower from the same bank.
        if (loanDao.getAll().containsKey(bank.getName() + "-" + borrower.getName())) {
            throw new LoanAlreadyGivenException("Loan already sanctioned to Borrower : " + borrower.getName() + " from bank : " + bank.getName());
        }
        else {
            Loan loan = new Loan(bank, borrower, principal, rateOfInterest, noOfYears);
            loan.process();
            this.loanDao.save(loan);
        }
    }

    public Loan getLoanDetails(String name) throws LoanDoesNotExistException {
        // Returns Loan details. Throws exception if there is no such loan given to the borrower from the inquired bank.
        Loan loan = this.loanDao.get(name);
        if (loan == null) {
            throw new LoanDoesNotExistException("Loan does not exist.");
        }
        return loan;
    }

    public String getBalance(Loan loan, int emiNumber) {
        // Returns the String with paid amount and remaining EMIs count upto the emi number passed in the method.
        double amountPaidTillThisEmi = 0.0;
        if (emiNumber != 0) {
            int i = 0;
            for (; i<emiNumber; i++) {
                amountPaidTillThisEmi += loan.getEmis().get(i).getPaidAmount();
            }
            return "" + loan.getBank() + " " + loan.getBorrower() + " " + (int)(amountPaidTillThisEmi + loan.getAdvanceAmount()) + " " + (loan.getEmis().get(i-1).getRemainingEmiCount());
        }
        else {
            return "" + loan.getBank() + " " + loan.getBorrower() + " " + (int)loan.getAdvanceAmount() + " " + (loan.getEmis().get(0).getRemainingEmiCount() + 1);
        }
    }

    public void advancePayment(Loan loan, double advanceAmount) {
        // Adds a payment if it was paid even before EMIs started.
        List<EMI> newEmis = new ArrayList<>();
        double paidAmount = advanceAmount;
        loan.setAdvanceAmount(loan.getAdvanceAmount() + advanceAmount);
        double remainingAmount = loan.getTotalAmount() - paidAmount;
        int leftEmiCount = (int) Math.ceil(remainingAmount / loan.getEmiAmount());
        while (leftEmiCount > 0) {
            leftEmiCount -= 1;
            newEmis.add(new EMI(Math.min(loan.getEmiAmount(), loan.getTotalAmount() - paidAmount), leftEmiCount));
            paidAmount += loan.getEmiAmount();
        }
        loan.setEmis(newEmis);
    }

    public void addLumpsumPayment(Loan loan, double lumpsumAmount, int emiNumber) {
        // Adds a lumpsum payment adjusted with the emi number passed in the method.
        if (emiNumber != 0) {
            List<EMI> newEmis = new ArrayList<>();
            double oldEmiAmount = loan.getEmis().get(emiNumber - 1).getPaidAmount();
            double newEmiAmount = oldEmiAmount + lumpsumAmount;
            double paidAmount = 0.0;
            for (int i = 0; i < emiNumber - 1; i++) {
                paidAmount += loan.getEmis().get(i).getPaidAmount();
                newEmis.add(loan.getEmis().get(i));
            }
            paidAmount += newEmiAmount;
            double remainingAmount = loan.getTotalAmount() - paidAmount;
            int leftEmiCount = (int) Math.ceil(remainingAmount / loan.getEmiAmount());
            newEmis.add(new EMI(newEmiAmount, leftEmiCount));
            while (leftEmiCount >= 0) {
                leftEmiCount -= 1;
                newEmis.add(new EMI(Math.min(loan.getEmiAmount(), loan.getTotalAmount() - paidAmount), leftEmiCount));
                paidAmount += loan.getEmiAmount();
            }
            loan.setEmis(newEmis);
        }
        else {
            advancePayment(loan, lumpsumAmount);
        }
    }


}
