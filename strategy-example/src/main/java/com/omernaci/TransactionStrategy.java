package com.omernaci;

import java.math.BigDecimal;

public interface TransactionStrategy {
    void execute(Account account, BigDecimal amount);
}
