# The Merchant Category Reward Engine

Modern credit cards offer different "Cashback" rates based on the Merchant Category Code (MCC).
You need to calculate the total rewards for a credit card user earned for a billing cycle due to spendings.

# Requirements:
- Each `Transaction` has
  - `amount`, and a 
  - `category` (e.g., `GROCERY`, `GAS`, `ENTERTAINMENT`, `DEPOSIT`, `OTHER`).

- Reward Rules:
  - **Groceries**: 3% cashback.
  - **Gas**: 2% cashback.
  - **All others**: 1% cashback.

The "Power User" Bonus: If the total spend across all categories for the month exceeds **$2,500**,
the user gets an additional flat 25.00 bonus added to their total rewards.
