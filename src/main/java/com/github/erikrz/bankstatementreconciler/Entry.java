package com.github.erikrz.bankstatementreconciler;

import java.math.BigDecimal;

public class Entry {
    private String transactionId;
    private BigDecimal amount;

    public Entry(String transactionId, BigDecimal amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
