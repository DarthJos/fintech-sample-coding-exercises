package com.github.erikrz.bankstatementreconciler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatementReconciler {
    public static void main(String[] args) {

    }

    public static String getReconciliationReport(List<Entry> internalLedger, List<Entry> externalStatement) {
        String reconciliationReport = "";
        List<String> missingExternallyIDs_List = getMissingExternallyIDs(internalLedger, externalStatement);
        List<String> missingInternallyIDs_List = getMissingInternallyIDs(internalLedger, externalStatement);
        List<String> mismatches = getMistmatchesIDs(internalLedger, externalStatement);

        reconciliationReport += "== Missing Externally IDs ==\n";
        for(String externallyId : missingExternallyIDs_List) reconciliationReport+=externallyId+"\n";
        reconciliationReport += "== Missing Internally IDs ==\n";
        for(String internallyId : missingInternallyIDs_List) reconciliationReport+=internallyId+"\n";
        reconciliationReport += "== Amount Mismatches IDs ==\n";
        for(String mismatchId : mismatches) reconciliationReport+= mismatchId +"\n";

        return reconciliationReport;
    }

    public static List<String> getMissingExternallyIDs(List<Entry> internalLedger, List<Entry> externalStatement){
        List<String> missingExternallyIDs = new ArrayList<>();

        for(Entry internalEntry:internalLedger) {
            String id = internalEntry.getTransactionId();

            boolean shouldAdd = true;
            for(Entry externalEntry:externalStatement){
                if (id.equals(externalEntry.getTransactionId())){
                    shouldAdd = false;
                    break;
                }
            }

            if (shouldAdd) missingExternallyIDs.add(id);
        }

        return missingExternallyIDs;
    }

    public static List<String> getMissingInternallyIDs(List<Entry> internalLedger, List<Entry> externalStatement){
        List<String> missingInternallyIDs = new ArrayList<>();

        for(Entry externalEntry:externalStatement) {
            String id = externalEntry.getTransactionId();

            boolean shouldAdd = true;
            for(Entry internalEntry:internalLedger){
                if (id.equals(internalEntry.getTransactionId())){
                    shouldAdd = false;
                    break;
                }
            }

            if (shouldAdd) missingInternallyIDs.add(id);
        }

        return missingInternallyIDs;
    }

    public static List<String> getMistmatchesIDs(List<Entry> internalLedger, List<Entry> externalStatement){
        List<String> mistmatchesIDs = new ArrayList<>();

        for(Entry externalEntry:externalStatement) {
            String externalId = externalEntry.getTransactionId();
            BigDecimal externalAmount = externalEntry.getAmount();

            for(Entry internalEntry: internalLedger){
                if (externalId.equals(internalEntry.getTransactionId()) && externalAmount != internalEntry.getAmount()){
                    mistmatchesIDs.add(externalId);
                }
            }
        }

        return mistmatchesIDs;
    }
}
