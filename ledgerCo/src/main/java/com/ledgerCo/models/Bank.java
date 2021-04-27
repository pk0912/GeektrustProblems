package com.ledgerCo.models;

public class Bank {

    private final String name;

    public Bank(String name) {
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
