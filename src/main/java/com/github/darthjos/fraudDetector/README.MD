# Fraudulent Transaction Detector

A bank wants to detect potentially fraudulent transactions based on unusual user behavior patterns.

## Requirements:

You are given a list:
- `List<Transaction> transactions`.

Each Transaction has
- `transactionId`,
- `userId`,
- `amount`,
- `timestamp` (epoch milliseconds or LocalDateTime),
- `location`.

Your must implement `detectFraud(List<Transaction> transactions)` method.

Returns:

- `High Frequency Transactions`: Identify users who performed more than 3 transactions within a 1-minute window.
- `Large Transactions`: Identify transactions where: `amount > 10,000`.
- `Rapid Location Changes` (Advanced): Flag transactions where
  - The same user makes two transactions within 5 minutes 
  - But from different locations.