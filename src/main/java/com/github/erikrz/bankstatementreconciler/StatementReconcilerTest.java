package com.github.erikrz.bankstatementreconciler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StatementReconcilerTest {
    public static void main(String[] args) {

        // Case 1 ---------------------------------------------------------------------------------------------------
        System.out.println("\nCase 1:");

        List<Entry> internal = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000001"), new BigDecimal("100")),
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000002"), new BigDecimal("200"))
        );

        List<Entry> external = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000001"), new BigDecimal("100")),
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000002"), new BigDecimal("200"))
        );

        System.out.println(StatementReconciler.getReconciliationReport(internal, external));

        // Case 2 ---------------------------------------------------------------------------------------------------
        System.out.println("\nCase 2:");

        internal = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000001"), new BigDecimal("100"))
        );

        external = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000002"), new BigDecimal("100"))
        );

        System.out.println(StatementReconciler.getReconciliationReport(internal, external));

        // Case 3 ---------------------------------------------------------------------------------------------------
        System.out.println("\nCase 3:");

        internal = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000001"), new BigDecimal("100"))
        );

        external = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000001"), new BigDecimal("999"))
        );

        System.out.println(StatementReconciler.getReconciliationReport(internal, external));

        // Case 4 ---------------------------------------------------------------------------------------------------
        System.out.println("\nCase 4:");

        internal = List.of(new Entry(UUID.randomUUID(), new BigDecimal("100")));
        external = List.of();

        System.out.println(StatementReconciler.getReconciliationReport(internal, external));

        // Case 5 ---------------------------------------------------------------------------------------------------
        System.out.println("\nCase 5:");
        internal = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000001"), new BigDecimal("100")),
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000002"), new BigDecimal("200")),
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000003"), new BigDecimal("300"))
        );

        external = List.of(
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000001"), new BigDecimal("999")), // mismatch
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000004"), new BigDecimal("200")), // missing internal
                new Entry(UUID.fromString("00000000-0000-0000-0000-000000000003"), new BigDecimal("300"))
        );

        System.out.println(StatementReconciler.getReconciliationReport(internal, external));

        // Case 6 ---------------------------------------------------------------------------------------------------
        System.out.println("\nCase 6:");

        internal = new ArrayList<>();
        external = new ArrayList<>();

        System.out.println(StatementReconciler.getReconciliationReport(internal, external));
    }
}
