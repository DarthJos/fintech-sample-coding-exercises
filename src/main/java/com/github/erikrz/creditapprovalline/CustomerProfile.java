package com.github.erikrz.creditapprovalline;

import java.math.BigDecimal;

public class CustomerProfile {
    private int age;
    private BigDecimal annualIncome;
    private int creditScore;
    private BigDecimal monthlyDebt;

    public CustomerProfile(int age, BigDecimal annualIncome, int creditScore, BigDecimal monthlyDebt) {
        this.age = age;
        this.annualIncome = annualIncome;
        this.creditScore = creditScore;
        this.monthlyDebt = monthlyDebt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public BigDecimal getMonthlyDebt() {
        return monthlyDebt;
    }

    public void setMonthlyDebt(BigDecimal monthlyDebt) {
        this.monthlyDebt = monthlyDebt;
    }
}
