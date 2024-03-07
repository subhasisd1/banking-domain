package com.mycompany.banking.domain;

import java.math.BigDecimal;

// Concrete CheckingAccount class
public class CheckingAccount extends Account {
    // Constructor
    public CheckingAccount(BigDecimal startingBalance, BigDecimal annualInterestRate) {
        super(startingBalance, annualInterestRate);
    }

    @Override
    public boolean makeWithdraw(BigDecimal amount) {
        if (currentBalance.compareTo(amount) >= 0) {
            BigDecimal withdrawalFee = new BigDecimal("0.50");
            currentBalance = currentBalance.subtract(amount.add(withdrawalFee));
            monthlyServiceCharge = monthlyServiceCharge.add(withdrawalFee);
            totalWithdrawals = totalWithdrawals.add(amount);
            numWithdrawals++;
            return true;
        }
        return false;
    }

    @Override
    public void makeDeposit(BigDecimal amount) {
        // Additional logic specific to CheckingAccount, if any...
        super.makeDeposit(amount);
    }


    @Override
    protected Account createCopy() {
        CheckingAccount copy = new CheckingAccount(startingBalance, annualInterestRate);
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