package com.mycompany.banking.domain;

import java.math.BigDecimal;

// Concrete SavingsAccount class
public class SavingsAccount extends Account {
    // Constructor
    public SavingsAccount(BigDecimal startingBalance, BigDecimal annualInterestRate) {
        super(startingBalance, annualInterestRate);
    }

    @Override
    public boolean makeWithdraw(BigDecimal amount) {
        if (currentBalance.compareTo(amount) >= 0) {
            if (numWithdrawals < 4) {
                currentBalance = currentBalance.subtract(amount);
            } else {
                BigDecimal withdrawalFee = new BigDecimal("1.00");
                currentBalance = currentBalance.subtract(amount.add(withdrawalFee));
                monthlyServiceCharge = monthlyServiceCharge.add(withdrawalFee);
            }
            totalWithdrawals = totalWithdrawals.add(amount);
            numWithdrawals++;
            return true;
        }
        return false;
    }

    @Override
    public void makeDeposit(BigDecimal amount) {
        // Additional logic specific to SavingsAccount, if any...
        super.makeDeposit(amount);
    }

    @Override
    protected Account createCopy() {
        SavingsAccount copy = new SavingsAccount(startingBalance, annualInterestRate);
        copy.currentBalance = currentBalance;
        copy.totalDeposits = BigDecimal.ZERO;
        copy.numDeposits = 0;
        copy.totalWithdrawals = BigDecimal.ZERO;
        copy.numWithdrawals = 0;
        copy.monthlyInterestAmount = monthlyInterestAmount;
        copy.monthlyServiceCharge = BigDecimal.ZERO;
        return copy;
    }
}