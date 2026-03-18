package com.github.erikrz.bankstatementreconciler;

import java.math.BigDecimal;
import java.util.UUID;

public class Entry {
    private UUID transactionID;
    private BigDecimal amount;

    public Entry(UUID transactionID, BigDecimal amount) {
        this.transactionID = transactionID;
        this.amount = amount;
    }

    public UUID getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(UUID transactionID) {
        this.transactionID = transactionID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
