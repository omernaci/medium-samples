package com.omernaci.accountaop.api;

import com.omernaci.accountaop.service.AccountService;
import com.omernaci.accountaop.service.dto.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable String accountNumber,
                                              @RequestParam BigDecimal amount) {
        AccountDto accountDto = accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable String accountNumber,
                                               @RequestParam BigDecimal amount) {
        AccountDto accountDto = accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String accountNumber) {
        BigDecimal balance = accountService.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }
}

