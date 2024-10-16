package com.omernaci.hibernatelistenerexample;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import java.math.BigDecimal;
import java.util.UUID;

public class AccountEntityListener {

    @PrePersist
    public void beforeCreate(Account account) {
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }

        if (account.getStatus() == null) {
            account.setStatus("PENDING");
        }

        if (account.getAccountNumber() == null) {
            account.setAccountNumber(generateUniqueAccountNumber());
        }
    }

    @PostPersist
    public void afterCreate(Account account) {
        System.out.println("Account with ID " + account.getId() + " and number " + account.getAccountNumber() + " has been created.");
        sendAccountCreationNotification(account);
    }

    private String generateUniqueAccountNumber() {
        return "ACCT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private void sendAccountCreationNotification(Account account) {
        System.out.println("Notification sent: Account " + account.getAccountNumber() + " created successfully.");
    }

}
