package com.ledgerCo.dao;

import com.ledgerCo.models.Borrower;

import java.util.HashMap;

public class BorrowerDao implements Dao<Borrower> {
    public HashMap<String, Borrower> borrowers = new HashMap<>();

    @Override
    public Borrower get(String name) {
        return borrowers.getOrDefault(name, null);
    }

    @Override
    public HashMap<String, Borrower> getAll() {
        return borrowers;
    }

    @Override
    public void save(Borrower borrower) {
        borrowers.put(borrower.getName(), borrower);
    }
}
