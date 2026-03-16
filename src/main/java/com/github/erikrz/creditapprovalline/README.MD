# The Credit Approval Pipeline

Automate the initial screening for a personal loan. 
You need to create an engine that evaluates a `CustomerProfile` and returns a `Decision`.

## Requirements:

- Define a CustomerProfile with: 
  - `age`, 
  - `annualIncome`, 
  - `creditScore`, and 
  - `monthlyDebt`.

- Approval Rules:
  - **Identity**: Must be at least 18 years old.
  - **Creditworthiness**: Credit score must be 650 or higher.
  - **Debt-to-Income (DTI) Ratio**: Monthly debt divided by monthly income (Annual Income / 12) must be less than or equal to 43%.
  
  $$\text{DTI} = \frac{\text{Monthly Debt}}{\text{Annual Income} / 12} \le 0.43$$

- Output: Return an object containing a boolean `isApproved` and a `List<String> rejectionReasons`.