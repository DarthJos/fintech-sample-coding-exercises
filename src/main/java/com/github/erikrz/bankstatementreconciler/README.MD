# The Bank Statement Reconciler

At the end of the month, a bank must "reconcile" its internal ledger against the record from the clearing house (external). You need to find the discrepancies.

## Requirements:

You are given two lists: 
- `List<Entry> internalLedger` and 
- `List<Entry> externalStatement`.

Each Entry has 
- a `transactionId` and 
- an `amount`.

Your function must return a `ReconciliationReport` identifying:

- `Missing Externally`: Transactions in your ledger but not in the external statement.
  - List of Missing IDs (that are in my ledger and not in the external)
- `Missing Internally`: Transactions in the external statement but not in your ledger.
  - List of Missing IDs (that are in external but not in my ledger)
- `Amount Mismatches`: Transactions where the ID exists in both, but the amounts do not match.
