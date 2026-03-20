package com.github.darthjos.fraudDetector;

import java.math.BigDecimal;
import java.util.*;

public class FraudDetector {

    public static String detectFraud(List<Transaction> transactions){
        if (transactions.isEmpty()) return "WARNING: No transactions where provided...";

        String fraudReport = "";
        fraudReport += getHighFrequencyTransactions(transactions);
        fraudReport += getLargeTransactionsList(transactions);
        getRapidLocationChangeTransaction(transactions);
        return fraudReport;
    }

    /**
     * Detect all transactions from same user withing a minute (60,000 milliseconds)
     * @param transactions
     * @return
     */
    public static String getHighFrequencyTransactions(List<Transaction> transactions) {
        // Declare time limit
        long limitWindow = 60000;

        //Create a map of users and their transactions
        Map<String, List<Transaction>> transactionsByUser_Map = new HashMap<>();

        for (Transaction transaction: transactions){
            // for each transaction, get the userId
            String userID = transaction.getUserID();

            // Check if the userID is already in the map and add the transaction

            if (!transactionsByUser_Map.containsKey(userID)) {
                List<Transaction> userTransactions = new ArrayList<>();
                transactionsByUser_Map.put(userID, userTransactions);
            }

            transactionsByUser_Map.get(userID).add(transaction);
        }

        // Create a set to store flagged users
        Set<String> flaggedUsers = new HashSet<>();

        // Process each user and its transactions
        for(Map.Entry<String, List<Transaction>> entry : transactionsByUser_Map.entrySet()){

            //get the list of transactions
            List<Transaction> userTransactions = entry.getValue();

            //order the transactions by timestampt
            userTransactions.sort(Comparator.comparing(Transaction::getTimestamp));

            //Check if more than 3 transactions where made in the time limit
            int start = 0;

            // Ventana deslizante
            for (int end = 0; end < userTransactions.size(); end++) {

                long tStart = userTransactions.get(start).getTimestamp();
                long tEnd = userTransactions.get(end).getTimestamp();

                // Si la ventana excede 1 minuto, mover el inicio
                while (tEnd - tStart > limitWindow) {
                    start++;
                    tStart = userTransactions.get(start).getTimestamp();
                }

                int windowSize = end - start + 1;

                // Si hay 3 o más transacciones dentro del minuto
                if (windowSize >= 3) {
                    flaggedUsers.add(entry.getKey());
                    break; // ya no necesitamos revisar más para este usuario
                }
            }
        }

        return "Flagged Users: " + flaggedUsers + "\n";
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

    public static String getRapidLocationChangeTransaction(List<Transaction> transactions){

        return "";
    }
}
