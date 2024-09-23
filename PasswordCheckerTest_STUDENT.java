package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 */
public class PasswordCheckerTest_STUDENT {

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    
    }

    /**
     * Test if the password is less than 6 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        String shortPassword = "abc";
        Throwable exception = assertThrows(LengthException.class, () -> {
            PasswordCheckerUtility.isValidPassword(shortPassword);
        });
        assertEquals("The password must be at least 6 characters long", exception.getMessage());
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        String passwordWithoutUpper = "abcdef";
        Throwable exception = assertThrows(NoUpperAlphaException.class, () -> {
            PasswordCheckerUtility.isValidPassword(passwordWithoutUpper);
        });
        assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        String passwordWithoutLower = "ABCDEF";
        Throwable exception = assertThrows(NoLowerAlphaException.class, () -> {
            PasswordCheckerUtility.isValidPassword(passwordWithoutLower);
        });
        assertEquals("The password must contain at least one lowercase alphabetic character", exception.getMessage());
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        String passwordWithSequence = "aaabbb";
        Throwable exception = assertThrows(InvalidSequenceException.class, () -> {
            PasswordCheckerUtility.isValidPassword(passwordWithSequence);
        });
        assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
    }

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        String passwordWithoutDigit = "Hello!";
        Throwable exception = assertThrows(NoDigitException.class, () -> {
            PasswordCheckerUtility.isValidPassword(passwordWithoutDigit);
        });
        assertEquals("The password must contain at least one digit", exception.getMessage());
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     * @throws PasswordException 
     */
    @Test
    public void testIsValidPasswordSuccessful() throws PasswordException {
        String validPassword = "Hello1!";
        assertTrue(PasswordCheckerUtility.isValidPassword(validPassword));
    }

    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testInvalidPasswords() {
        ArrayList<String> passwords = new ArrayList<>(Arrays.asList("abc", "ABC", "Hello!", "aaabbb", "Hello1"));
        ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwords);

        assertEquals("abc The password must be at least 6 characters long", invalidPasswords.get(0));
        assertEquals("Hello! The password must contain at least one digit", invalidPasswords.get(2));
        assertEquals("aaabbb The password cannot contain more than two of the same character in sequence", invalidPasswords.get(3));
    }
}