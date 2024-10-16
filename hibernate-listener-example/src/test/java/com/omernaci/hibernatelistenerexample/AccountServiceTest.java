package com.omernaci.hibernatelistenerexample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testCreateAccountWithDefaults() {
        Account account = new Account();

        Account savedAccount = accountService.createAccount(account);

        assertEquals(BigDecimal.ZERO, savedAccount.getBalance());
        assertEquals("PENDING", savedAccount.getStatus());
        assertNotNull(savedAccount.getAccountNumber());
    }
}