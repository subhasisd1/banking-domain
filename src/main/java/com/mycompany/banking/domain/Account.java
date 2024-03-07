package com.mycompany.banking.domain;

import java.math.BigDecimal;

// Abstract Account class
public abstract class Account {
    protected BigDecimal startingBalance;
    protected BigDecimal currentBalance;
    protected BigDecimal totalDeposits;
    protected int numDeposits;
    protected BigDecimal totalWithdrawals;
    protected int numWithdrawals;
    protected BigDecimal annualInterestRate;
    protected BigDecimal monthlyInterestAmount;
    protected BigDecimal monthlyServiceCharge;

    // Constructor
    public Account(BigDecimal startingBalance, BigDecimal annualInterestRate) {
        this.startingBalance = startingBalance;
        this.currentBalance = startingBalance;
        this.totalDeposits = BigDecimal.ZERO;
        this.numDeposits = 0;
        this.totalWithdrawals = BigDecimal.ZERO;
        this.numWithdrawals = 0;
        this.annualInterestRate = annualInterestRate;
        this.monthlyInterestAmount = BigDecimal.ZERO;
        this.monthlyServiceCharge = BigDecimal.ZERO;
    }

    // Abstract method for making withdrawals
    public abstract boolean makeWithdraw(BigDecimal amount);

    // Method to calculate interest
    public void calculateInterest() {
        BigDecimal monthlyInterestRate = annualInterestRate.divide(new BigDecimal("12"), 4, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthlyInterest = currentBalance.multiply(monthlyInterestRate);
        currentBalance = currentBalance.add(monthlyInterest);
        monthlyInterestAmount = monthlyInterestAmount.add(monthlyInterest);
    }

    public void makeDeposit(BigDecimal amount) {
        currentBalance = currentBalance.add(amount);
        totalDeposits = totalDeposits.add(amount);
        numDeposits++;
    }
    // Method to perform monthly report
    public Account doMonthlyReport() {
        if (this instanceof CheckingAccount) {
            monthlyServiceCharge = monthlyServiceCharge.add(new BigDecimal("5.00"));
        }
        calculateInterest();
        Account copy = createCopy();
        monthlyReset();
        return copy;
    }

    // Method to create a deep copy of the account
    protected abstract Account createCopy();

    // Method to reset account fields for the next month
    private void monthlyReset() {
        startingBalance = currentBalance;
        totalDeposits = BigDecimal.ZERO;
        numDeposits = 0;
        totalWithdrawals = BigDecimal.ZERO;
        numWithdrawals = 0;
        monthlyInterestAmount = BigDecimal.ZERO;
        monthlyServiceCharge = BigDecimal.ZERO;
    }

    // Getter for current balance
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    // Method to generate monthly report
    public String generateMonthlyReport() {
        return "Starting Balance: $" + startingBalance +
                "\nCurrent Balance: $" + currentBalance +
                "\nTotal Deposits: $" + totalDeposits +
                "\nNumber of Deposits: " + numDeposits +
                "\nTotal Withdrawals: $" + totalWithdrawals +
                "\nNumber of Withdrawals: " + numWithdrawals +
                "\nAnnual Interest Rate: " + annualInterestRate +
                "\nMonthly Interest Amount: $" + monthlyInterestAmount +
                "\nMonthly Service Charge: $" + monthlyServiceCharge;
    }
}