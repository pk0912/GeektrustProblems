package com.ledgerCo.services;

import com.ledgerCo.models.Borrower;
import com.ledgerCo.dao.BorrowerDao;

public class BorrowerService {
    private final BorrowerDao borrowerDao;

    public BorrowerService(BorrowerDao borrowerDao) {
        this.borrowerDao = borrowerDao;
    }

    public Borrower getBorrower(String name) {
        // Returns Borrower object using borrower name. If borrower is not present, creates a new Borrower object and return that.
        Borrower newBorrower;
        if (borrowerDao.getAll().containsKey(name.trim())) {
            newBorrower = borrowerDao.get(name.trim());
        }
        else {
            newBorrower = new Borrower(name.trim());
            borrowerDao.save(newBorrower);
        }
        return newBorrower;
    }
}
