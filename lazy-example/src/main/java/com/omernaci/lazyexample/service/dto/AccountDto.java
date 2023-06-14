package com.omernaci.lazyexample.service.dto;

import com.omernaci.lazyexample.persistence.entity.AccountType;
import java.math.BigDecimal;

public record AccountDto(String accountNumber, BigDecimal balance, String accountHolderName, AccountType accountType) {
}