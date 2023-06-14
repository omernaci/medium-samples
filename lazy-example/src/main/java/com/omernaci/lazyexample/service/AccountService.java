package com.omernaci.lazyexample.service;

import com.omernaci.lazyexample.service.dto.AccountDto;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    Optional<AccountDto> getAccountById(Long id);

    AccountDto saveAccount(AccountDto accountDto);

    void deleteAccount(Long id);

}
