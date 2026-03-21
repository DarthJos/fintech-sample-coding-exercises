package com.github.erikrz.bankstatementreconciler;

import java.math.BigDecimal;
import java.util.*;

public class StatementReconciler {

    public static String getReconciliationReport (List<Entry> internalLedger, List<Entry> externalStatement) {
        // Create hashmaps of each lists
        HashMap<UUID, BigDecimal> internalTransactions = new HashMap<>();
        HashMap<UUID, BigDecimal> externalTransactions = new HashMap<>();

        //Create lists for the report
        ArrayList<String> missingExternally = new ArrayList<>();
        ArrayList<String> missingInternally = new ArrayList<>();
        ArrayList<String> amountMismatches = new ArrayList<>();

        // As the reports are from the same range of time, lets suppose
        // both List are of same size. If not, send and alert, exception, etc
        if (internalLedger.isEmpty() && externalStatement.isEmpty()) return "WARNING: The reports are empty...";
        if (internalLedger.size() != externalStatement.size()) return "WARNING: Reports have different amount of transactions...";

        // Initialize maps
        for (int i = 0; i < internalLedger.size(); i++) {

            // Fill internalMap
            UUID internal_txID = internalLedger.get(i).getTransactionID();
            BigDecimal internal_txAmount = internalLedger.get(i).getAmount();
            internalTransactions.put(internal_txID, internal_txAmount);

            // Fill externalMap
            UUID external_txID = externalStatement.get(i).getTransactionID();
            BigDecimal external_txAmount = externalStatement.get(i).getAmount();
            externalTransactions.put(external_txID, external_txAmount);
        }

        // As both maps are from the same size, we can iterate over both at the same time
        for (int i = 0; i < internalLedger.size(); i++) {

            // Check if internal tx is in external, if not, add to a list of missingExternally
            UUID internal_txID = internalLedger.get(i).getTransactionID();
            if (!externalTransactions.containsKey(internal_txID)){
                missingExternally.add(String.valueOf(internal_txID));
            }

            // Check if external tx is in internal, if not, add to a list of missingInternally
            UUID external_txID = externalStatement.get(i).getTransactionID();
            if (!internalTransactions.containsKey(external_txID)){
                missingInternally.add(String.valueOf(external_txID));
            }

            // Check if amounts of internal and external match, if not, add to list of amountMismatches
            else {
                if (!internalTransactions.get(external_txID).equals(externalTransactions.get(external_txID))) {
                    amountMismatches.add(String.valueOf(external_txID));
                }
            }
        }

        return
                " == Missing Externally ==" +
                missingExternally + "\n" +
                " == Missing Internally ==" +
                missingInternally + "\n" +
                " == Amount Mismatches ==" +
                amountMismatches;
    }
}
