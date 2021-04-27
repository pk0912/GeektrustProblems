package com.ledgerCo.models;

public class EMI {
    private final double paidAmount;
    private final int remainingEmiCount;

    public EMI(double paidAmount, int remainingEmiCount) {
        this.paidAmount = paidAmount;
        this.remainingEmiCount = remainingEmiCount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public int getRemainingEmiCount() {
        return remainingEmiCount;
    }
}
