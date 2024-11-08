package com.omernaci.parameterizedtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AccountValidationServiceTest {

    @InjectMocks
    private AccountValidationService accountValidationService;

    @DisplayName("Test validateAccountNumber with valid account numbers")
    @ParameterizedTest(name = "Valid Account Number: {0}")
    @ValueSource(strings = {"ACC1234567", "ACC0000000", "ACC9876543"})
    void testValidateAccountNumber_Valid(String accountNumber) {
        assertTrue(accountValidationService.validateAccountNumber(accountNumber));
    }

}