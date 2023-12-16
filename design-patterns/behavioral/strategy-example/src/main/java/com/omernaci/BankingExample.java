package com.omernaci;

import java.math.BigDecimal;

public class BankingExample {
    public static void main(String[] args) {
        Account account = new Account(BigDecimal.valueOf(100));

        // Deposit $50 using the DepositStrategy
        account.setTransactionStrategy(new DepositStrategy());
        account.executeTransaction(BigDecimal.valueOf(50));
        System.out.println("Balance after deposit: $" + account.getBalance());

        // Withdraw $25 using the WithdrawalStrategy
        account.setTransactionStrategy(new WithdrawalStrategy());
        account.executeTransaction(BigDecimal.valueOf(25));
        System.out.println("Balance after withdrawal: $" + account.getBalance());
    }
}