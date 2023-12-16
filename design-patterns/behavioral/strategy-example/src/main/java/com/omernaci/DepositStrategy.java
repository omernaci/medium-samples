package com.omernaci;

import java.math.BigDecimal;

public class DepositStrategy implements TransactionStrategy {

    @Override
    public void execute(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
    }
}
