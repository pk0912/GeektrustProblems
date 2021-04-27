package com.ledgerCo.services;

import com.ledgerCo.models.Bank;
import com.ledgerCo.dao.BankDao;

public class BankService {
    private final BankDao bankDao;

    public BankService(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public Bank getBank(String name) {
        // Returns bank object using bank name. If bank is not present, creates a new bank object and return that.
        Bank newBank;
        if (bankDao.getAll().containsKey(name.trim())) {
            newBank = bankDao.getAll().get(name.trim());
        }
        else {
            newBank = new Bank(name.trim());
            bankDao.save(newBank);
        }
        return newBank;
    }

}
