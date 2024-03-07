package com.mycompany.banking.domain;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankingDomain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBank Menu");
            System.out.println("A: Savings");
            System.out.println("B: Checking");
            System.out.println("C: Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.next().toUpperCase();

            switch (choice) {
                case "A":
                    handleAccount(scanner, new SavingsAccount(new BigDecimal("1000"), new BigDecimal("0.05")));
                    break;
                case "B":
                    handleAccount(scanner, new CheckingAccount(new BigDecimal("2000"), new BigDecimal("0.03")));
                    break;
                case "C":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleAccount(Scanner scanner, Account account) {
        while (true) {
            System.out.println("\nAccount Menu");
            System.out.println("A: Deposit");
            System.out.println("B: Withdrawal");
            System.out.println("C: Report");
            System.out.println("D: Return to Bank Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.next().toUpperCase();

            switch (choice) {
                case "A":
                    System.out.print("Enter deposit amount: ");
                    BigDecimal depositAmount = scanner.nextBigDecimal();
                    account.makeDeposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case "B":
                    System.out.print("Enter withdrawal amount: ");
                    BigDecimal withdrawalAmount = scanner.nextBigDecimal();
                    boolean withdrawalStatus = account.makeWithdraw(withdrawalAmount);
                    if (withdrawalStatus) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient funds. Withdrawal failed.");
                        System.out.println("Remaining balance: $" + account.getCurrentBalance());
                    }
                    break;
                case "C":
                    System.out.println("Generating monthly report...");
                    Account copy = account.doMonthlyReport();
                    System.out.println("Monthly Report:");
                    System.out.println(copy.generateMonthlyReport());
                    break;
                case "D":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
