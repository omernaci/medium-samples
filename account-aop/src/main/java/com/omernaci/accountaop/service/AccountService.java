package com.omernaci.accountaop.service;


import com.omernaci.accountaop.service.dto.AccountDto;

import java.math.BigDecimal;

public interface AccountService {

    AccountDto deposit(String accountNumber, BigDecimal amount);

    AccountDto withdraw(String accountNumber, BigDecimal amount);

    BigDecimal getBalance(String accountNumber);

}
