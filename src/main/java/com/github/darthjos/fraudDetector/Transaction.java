package com.github.darthjos.fraudDetector;

import java.math.BigDecimal;

public class Transaction {
    private String transactionID;
    private String userID;
    private BigDecimal amount;
    private long timestamp;
    private String location;

    public Transaction(String transactionID, String userID, BigDecimal amount, long timestamp, String location) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.amount = amount;
        this.timestamp = timestamp;
        this.location = location;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
