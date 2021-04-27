package com.ledgerCo.models;

import java.util.ArrayList;
import java.util.List;

public class Loan {
    private final Bank bank;
    private final Borrower borrower;
    private final double principal;
    private final double rateOfInterest;
    private final int noOfYears;
    private double emiAmount;
    private double totalAmount;
    private double advanceAmount = 0.0;
    private List<EMI> emis = new ArrayList<>();

    public Loan(Bank bank, Borrower borrower, double principal, double rateOfInterest, int noOfYears) {
        this.bank = bank;
        this.borrower = borrower;
        this.principal = principal;
        this.rateOfInterest = rateOfInterest;
        this.noOfYears = noOfYears;
    }

    public Bank getBank() {
        return bank;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public List<EMI> getEmis() {
        return emis;
    }

    public void setEmis(List<EMI> emis) {
        this.emis = emis;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public double calcInterest() {
        return principal * rateOfInterest * noOfYears / 100;
    }

    public void calcTotalAmount() {
        totalAmount = principal + calcInterest();
    }

    public void calcEmiAmount() {
        emiAmount = Math.ceil(totalAmount / (noOfYears * 12));
    }

    public void populateEmis() {
        double paidAmount = 0.0;
        for (int i=0; i< noOfYears * 12; i++) {
            emis.add(new EMI(Math.min(emiAmount, totalAmount - paidAmount), noOfYears * 12 - i - 1));
            paidAmount += emiAmount;
        }
    }

    public void process() {
        calcTotalAmount();
        calcEmiAmount();
        populateEmis();
    }

}
