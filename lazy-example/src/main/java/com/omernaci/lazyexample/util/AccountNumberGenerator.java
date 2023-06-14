package com.omernaci.lazyexample.util;

import java.security.SecureRandom;

public final class AccountNumberGenerator {

    private static final int ACCOUNT_NUMBER_LENGTH = 10;

    private AccountNumberGenerator() { }

    public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int digit = secureRandom.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }

}
