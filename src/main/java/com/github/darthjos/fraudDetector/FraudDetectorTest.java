package com.github.darthjos.fraudDetector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FraudDetectorTest {

    public static void main(String[] args) {

        // Case 1: High Frequency ---------------------------------------------------------
        System.out.println("\nCase 1: High Frequency --------------------------------------------------");

        List<Transaction> transactions = List.of(
                new Transaction("t1", "user1", new BigDecimal("100"), 1000, "MX"),
                new Transaction("t3", "user1", new BigDecimal("300"), 3000, "MX"),
                new Transaction("t2", "user1", new BigDecimal("200"), 2000, "MX"),
                new Transaction("t4", "user4", new BigDecimal("400"), 4000, "MX"),
                new Transaction("t5", "user3", new BigDecimal("500"), 4000, "MX"),
                new Transaction("t6", "user2", new BigDecimal("600"), 4000, "MX"),
                new Transaction("t7", "user1", new BigDecimal("700"), 4000, "MX")
        );

        System.out.println(FraudDetector.detectFraud(transactions));

        // Expected:
        // High Frequency Users: [user1]


        // Case 2: Large Transactions -----------------------------------------------------
        System.out.println("\nCase 2: Large Transactions --------------------------------------------------");

        transactions = List.of(
                new Transaction("t1", "user1", new BigDecimal("15000"), 1000, "MX"),
                new Transaction("t2", "user2", new BigDecimal("500"), 2000, "MX")
        );

        System.out.println(FraudDetector.detectFraud(transactions));

        // Expected:
        // Large Transactions: [t1]


        // Case 3: Rapid Location Change --------------------------------------------------
        System.out.println("\nCase 3: Rapid Location Change --------------------------------------------------");

        transactions = List.of(
                new Transaction("t1", "user1", new BigDecimal("100"), 1000, "MX"),
                new Transaction("t2", "user1", new BigDecimal("200"), 200000, "US") // within 5 min
        );

        System.out.println(FraudDetector.detectFraud(transactions));

        // Expected:
        // Suspicious Location Changes: [user1 or transaction ids]


        // Case 4: Mixed Scenario ---------------------------------------------------------
        System.out.println("\nCase 4: Mixed Scenario --------------------------------------------------");

        transactions = List.of(
                // High frequency
                new Transaction("t1", "user1", new BigDecimal("100"), 1000, "MX"),
                new Transaction("t2", "user1", new BigDecimal("200"), 2000, "MX"),
                new Transaction("t3", "user1", new BigDecimal("300"), 3000, "MX"),
                new Transaction("t4", "user1", new BigDecimal("400"), 4000, "MX"),

                // Large transaction
                new Transaction("t5", "user2", new BigDecimal("20000"), 5000, "MX"),

                // Location change
                new Transaction("t6", "user3", new BigDecimal("100"), 6000, "MX"),
                new Transaction("t7", "user3", new BigDecimal("200"), 7000, "CA")
        );

        System.out.println(FraudDetector.detectFraud(transactions));


        // Case 5: No Fraud --------------------------------------------------------------
        System.out.println("\nCase 5: No Fraud --------------------------------------------------");

        transactions = List.of(
                new Transaction("t1", "user1", new BigDecimal("100"), 1000, "MX"),
                new Transaction("t2", "user1", new BigDecimal("200"), 70000, "MX"),
                new Transaction("t3", "user2", new BigDecimal("300"), 150000, "US")
        );

        System.out.println(FraudDetector.detectFraud(transactions));

        // Expected:
        // All lists empty


        // Case 6: Empty Input -----------------------------------------------------------
        System.out.println("\nCase 6: Empty Input --------------------------------------------------");

        transactions = new ArrayList<>();

        System.out.println(FraudDetector.detectFraud(transactions));

        // Expected:
        // empty report or warning


        // Case 7: Edge Case (exact boundary) --------------------------------------------
        System.out.println("\nCase 7: Edge Case - Boundary --------------------------------------------------");

        transactions = List.of(
                new Transaction("t1", "user1", new BigDecimal("100"), 0, "MX"),
                new Transaction("t2", "user1", new BigDecimal("200"), 60000, "MX"), // exactly 1 min
                new Transaction("t3", "user1", new BigDecimal("300"), 120000, "MX")
        );

        System.out.println(FraudDetector.detectFraud(transactions));

        // Expected:
        // Depends on implementation (inclusive vs exclusive window)
    }
}
