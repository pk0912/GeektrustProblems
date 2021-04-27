package com.ledgerCo.dao;

import com.ledgerCo.models.Loan;

import java.util.HashMap;

public class LoanDao implements Dao<Loan> {
    private final HashMap<String, Loan> loans = new HashMap<>();

    @Override
    public Loan get(String name) {
        return loans.getOrDefault(name, null);
    }

    @Override
    public HashMap<String, Loan> getAll() {
        return loans;
    }

    @Override
    public void save(Loan loan) {
        loans.put(loan.getBank() + "-" + loan.getBorrower(), loan);
    }
}
