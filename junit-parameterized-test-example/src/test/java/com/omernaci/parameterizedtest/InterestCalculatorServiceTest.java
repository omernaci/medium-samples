package com.omernaci.parameterizedtest;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class InterestCalculatorServiceTest {

    @InjectMocks
    private InterestCalculatorService calculatorService;

    @DisplayName("Parameterized Test for calculateInterest with BigDecimal")
    @ParameterizedTest(name = "{index} => accountType={0}, balance={1}, expectedInterest={2}")
    @CsvSource({
        "SAVINGS, 1000, 40.00",
        "CURRENT, 1000, 20.00",
        "FIXED, 1000, 60.00"
    })
    public void calculateInterestTest(String accountType, String balance, String expectedInterest) {
        BigDecimal balanceBD = new BigDecimal(balance);
        BigDecimal expectedInterestBD = new BigDecimal(expectedInterest);

        BigDecimal calculatedInterest = calculatorService.calculateInterest(accountType, balanceBD);

        assertEquals(expectedInterestBD, calculatedInterest);
    }

}