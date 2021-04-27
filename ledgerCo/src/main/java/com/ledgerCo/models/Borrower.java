package com.ledgerCo.models;

public class Borrower {
    private final String name;

    public Borrower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
