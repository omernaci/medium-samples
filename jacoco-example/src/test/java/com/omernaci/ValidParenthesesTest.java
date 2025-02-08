package com.omernaci;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesTest {

    private final ValidParentheses validParentheses = new ValidParentheses();

    @Test
    void shouldReturnTrueForValidSimpleParentheses() {
        assertTrue(validParentheses.isValid("()"));
    }

    @Test
    void shouldReturnTrueForValidMixedParentheses() {
        assertTrue(validParentheses.isValid("()[]{}"));
    }

    @Test
    void shouldReturnFalseForMismatchedParentheses() {
        assertFalse(validParentheses.isValid("(]"));
    }

    @Test
    void shouldReturnFalseForIncorrectNesting() {
        assertFalse(validParentheses.isValid("([)]"));
    }

    @Test
    void shouldReturnTrueForProperlyNestedBraces() {
        assertTrue(validParentheses.isValid("{[]}"));
    }

    //@Test
    void shouldReturnFalseForUnclosedParentheses() {
        assertFalse(validParentheses.isValid("({"));
    }

    //@Test
    void shouldReturnFalseForExtraClosingParentheses() {
        assertFalse(validParentheses.isValid("({})]"));
    }

    //@Test
    void shouldReturnTrueForEmptyString() {
        assertTrue(validParentheses.isValid(""));
    }

    //@Test
    void shouldReturnFalseForSingleOpeningBracket() {
        assertFalse(validParentheses.isValid("("));
    }

}
