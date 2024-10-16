package com.omernaci.hibernatelistenerexample;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

}