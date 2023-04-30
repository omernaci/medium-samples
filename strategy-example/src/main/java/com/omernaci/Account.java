package com.omernaci;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private TransactionStrategy transactionStrategy;

    public Account(BigDecimal initialBalance) {
        this.balance = initialBalance;
    }

    public void setTransactionStrategy(TransactionStrategy transactionStrategy) {
        this.transactionStrategy = transactionStrategy;
    }

    public void executeTransaction(BigDecimal amount) {
        this.transactionStrategy.execute(this, amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}