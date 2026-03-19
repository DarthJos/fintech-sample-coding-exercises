package com.github.darthjos.fraudDetector;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FraudDetector {

    public static String detectFraud(List<Transaction> transactions){
        if (transactions.isEmpty()) return "WARNING: No transactions where provided...";

        String fraudReport = "";
        getHighFrequencyTransactions(transactions);
        fraudReport += getLargeTransactionsList(transactions);
        //getRapidLocationChangeTransaction(transactions);
        return fraudReport;
    }

    /**
     * Detect all transactions from same user withing a minute (1000 milliseconds)
     * @param transactions
     * @return
     */
    public static String getHighFrequencyTransactions(List<Transaction> transactions) {


        return "";
    }

    /**
     * Method to detect all transactions with amount > 10000
     * @param transactions
     * @return
     */
    public static String getLargeTransactionsList(List<Transaction> transactions){

        BigDecimal topLimit = new BigDecimal(10000);
        List<String> largeTransactionsList = transactions.stream()
                .filter(t -> t.getAmount().compareTo(topLimit)>0)
                .map(t-> t.getTransactionID().toString())
                .toList();

        return "Large Transactions: " + largeTransactionsList;
    }
}
