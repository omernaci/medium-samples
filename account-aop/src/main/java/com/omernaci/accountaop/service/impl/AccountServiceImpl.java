package com.omernaci.accountaop.service.impl;

import com.omernaci.accountaop.persistence.entity.Account;
import com.omernaci.accountaop.persistence.repository.AccountRepository;
import com.omernaci.accountaop.service.AccountService;
import com.omernaci.accountaop.service.dto.AccountDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository,
                              ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public AccountDto deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return modelMapper.map(account, AccountDto.class);
    }

    @Transactional
    @Override
    public AccountDto withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        return modelMapper.map(account, AccountDto.class);
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account number"));
        return account.getBalance();
    }
}
