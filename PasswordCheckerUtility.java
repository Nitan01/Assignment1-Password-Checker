package application;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    /* @author Nitan Touch */

// PasswordCheckerUtility class for checking password validity and weakness
public class PasswordCheckerUtility {

    /**
     * Compares two passwords for equality.
     *
     * @param password        the first password
     * @param passwordConfirm the password to confirm
     * @throws UnmatchedException if the passwords do not match
     */
    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if (!password.equals(passwordConfirm)) {
            throw new UnmatchedException("Passwords do not match");
        }
    }

    /**
     * Compares two passwords and returns true if they match.
     *
     * @param password        the first password
     * @param passwordConfirm the password to confirm
     * @return true if the passwords match, false otherwise
     */
    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    /**
     * Checks if the password is valid in length.
     *
     * @param password the password to check
     * @return true if the password length is valid
     * @throws LengthException if the password is less than 6 characters
     */
    public static boolean isValidLength(String password) throws LengthException {
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long");
        }
        return true;
    }

    /**
     * Checks if the password contains at least one uppercase letter.
     *
     * @param password the password to check
     * @return true if the password contains an uppercase letter
     * @throws NoUpperAlphaException if no uppercase letter is found
     */
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
        if (!password.chars().anyMatch(Character::isUpperCase)) {
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        }
        return true;
    }

    /**
     * Checks if the password contains at least one lowercase letter.
     *
     * @param password the password to check
     * @return true if the password contains a lowercase letter
     * @throws NoLowerAlphaException if no lowercase letter is found
     */
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
        if (!password.chars().anyMatch(Character::isLowerCase)) {
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
        }
        return true;
    }

    /**
     * Checks if the password contains at least one digit.
     *
     * @param password the password to check
     * @return true if the password contains a digit
     * @throws NoDigitException if no digit is found
     */
    public static boolean hasDigit(String password) throws NoDigitException {
        if (!password.chars().anyMatch(Character::isDigit)) {
            throw new NoDigitException("The password must contain at least one digit");
        }
        return true;
    }

    /**
     * Checks if the password contains more than two of the same character in sequence.
     *
     * @param password the password to check
     * @return true if the password does not contain such sequences
     * @throws InvalidSequenceException if there are more than two of the same character in sequence
     */
    public static boolean noSameCharInSequence(String password) throws InvalidSequenceException {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
            }
        }
        return true;
    }

    /**
     * Checks if the password contains at least one special character.
     *
     * @param password the password to check
     * @return true if the password contains a special character
     * @throws NoSpecialCharacterException if no special character is found
     */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {  // If no special character is found
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        }
        return true;
    }

    /**
     * Validates the password against all rules.
     *
     * @param password the password to validate
     * @return true if the password is valid
     * @throws PasswordException if the password is invalid
     */
    public static boolean isValidPassword(String password) throws PasswordException {
        if (password.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long");
        }

        noSameCharInSequence(password);
        hasUpperAlpha(password);
        hasLowerAlpha(password);
        hasDigit(password);
        hasSpecialChar(password);

        return true;
    }

    /**
     * Checks if the password length is between 6 and 9 characters.
     *
     * @param password the password to check
     * @return true if the password length is between 6 and 9 characters
     */
    public static boolean hasBetweenSixAndNineChars(String password) {
        return password.length() >= 6 && password.length() <= 9;
    }

    /**
     * Checks if the password is weak.
     *
     * @param password the password to check
     * @return true if the password is weak
     * @throws WeakPasswordException if the password is weak
     */
    public static boolean isWeakPassword(String password) throws WeakPasswordException {
        if (hasBetweenSixAndNineChars(password)) {
            throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
        }
        return false;
    }

    /**
     * Checks a list of passwords and returns the invalid ones.
     *
     * @param passwords the list of passwords to check
     * @return a list of invalid passwords along with their error messages
     */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();
        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (PasswordException e) {
                invalidPasswords.add(password + " " + e.getMessage());
            }
        }
        return invalidPasswords;
    }
}

// Exception Classes

class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}

class LengthException extends PasswordException {
    public LengthException(String message) {
        super(message);
    }
}

class NoUpperAlphaException extends PasswordException {
    public NoUpperAlphaException(String message) {
        super(message);
    }
}

class NoLowerAlphaException extends PasswordException {
    public NoLowerAlphaException(String message) {
        super(message);
    }
}

class NoDigitException extends PasswordException {
    public NoDigitException(String message) {
        super(message);
    }
}

class NoSpecialCharacterException extends PasswordException {
    public NoSpecialCharacterException(String message) {
        super(message);
    }
}

class InvalidSequenceException extends PasswordException {
    public InvalidSequenceException(String message) {
        super(message);
    }
}

class WeakPasswordException extends PasswordException {
    public WeakPasswordException(String message) {
        super(message);
    }
}

class UnmatchedException extends PasswordException {
    public UnmatchedException(String message) {
        super(message);
    }
}