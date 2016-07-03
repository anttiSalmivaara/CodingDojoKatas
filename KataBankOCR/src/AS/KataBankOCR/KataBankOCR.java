package AS.KataBankOCR;


import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Coding Dojo Kata Bank OCR kata.
 * 
 * @author Antti
 */
public class KataBankOCR {

    // The first method implements the User Story 1 of the Kata.
    public String readToNumber(String input) {

        StringBuilder result = new StringBuilder();

        // Read the input to individual digits.
        List<String> digits = readToList(input);

        // Get the digits from the Digits class.
        for (String s : digits) {
            result.append(Digits.getDigit(s));
        }

        // Return the result number.
        return result.toString();

    }

    // The second method implements the User Story 2.
    // It is not needed elsewhere so declared private.
    private Boolean checksumChecker(String number) {

        int checksum = 0;

        for (int i = 1; i <= number.length(); i++) {
            int d = Character.getNumericValue(number.charAt(number.length() - i));
            checksum += i * d;
        }

        return checksum % 11 == 0;
    }

    // The third method implements the User Story 3.
    // Straightforward flagging of the erroneous account numbers.
    public String readAccountNumber(String input) {
        String accountNumber = readToNumber(input);

        if (accountNumber.contains("?")) {
            return accountNumber + " ILL";
        } else if (checksumChecker(accountNumber)) {
            return accountNumber;
        } else {
            return accountNumber + " ERR";
        }
    }

    // Finally the fourth method implements the User Story 4.
    // To avoid repeating, does the same checks as the methods of the previous
    // user stories.
    public String resolveAccountNumber(String input) {

        StringBuilder result = new StringBuilder();

        // Read the input to individual digits.
        List<String> digits = readToList(input);

        // Get the digits from the Digits class.
        for (String s : digits) {
            result.append(Digits.getDigit(s));
        }

        String accountNumber = result.toString();

        // Go through the cases, starting with the case in which there is a 
        // mistake in the digit-representation.
        if (accountNumber.contains("?")) {
            if (accountNumber.indexOf("?") != accountNumber.lastIndexOf("?")) {
                // If there are two mistakes, according to the instructions we are
                // not going to start guessing.

                accountNumber = result.append(" ILL").toString();
            } else {
                // We check the variations for the digit resulting in the
                // ?-representation.
                
                List<String> possibleAccountNumbers = getPossibleNumbers(accountNumber, digits, accountNumber.indexOf("?"));

                // If we get multiple possible account numbers, the result is
                // AMB-flagged number. If we get one, we return it. If there is
                // no possible numbers, we return te original representation with
                // the ILL-flag.
                
                if (possibleAccountNumbers.isEmpty()) {
                    accountNumber = result.append(" ILL").toString();
                } else if (possibleAccountNumbers.size() == 1) {
                    accountNumber = possibleAccountNumbers.get(0);
                } else {
                    accountNumber = result.append(" AMB").toString();
                }
            }
        } else if (!checksumChecker(accountNumber)) {
            // If the account number does not pass the checksum test, we start
            // variating the digits in order to get the correct number.
            
            List<String> possibleAccountNumbers = new ArrayList<>();

            // We variate every digit.
            for (int i = 0; i < 9; i++) {
                possibleAccountNumbers.addAll(getPossibleNumbers(accountNumber, digits, i));
            }

            if (possibleAccountNumbers.isEmpty()) {
                accountNumber = result.append(" ERR").toString();
            } else if (possibleAccountNumbers.size() == 1) {
                accountNumber = possibleAccountNumbers.get(0);
            } else {
                accountNumber = result.append(" AMB").toString();
            }

        }

        return accountNumber;
    }

    // Helper method for reading the input into separate digits and returns them
    // as a list.
    private List<String> readToList(String input) {

        // Chop every line in to its own String.
        String[] inputArray = input.split("\n");

        // Build the digit-to-be-read line at a time.
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        StringBuilder line4 = new StringBuilder();

        // Accumulate the result one digit at a time.
        List<String> result = new ArrayList<>();

        // Read three characters at a time
        int counter = 0;
        for (int i = 0; i < 27; i++) {
            line1.append(inputArray[0].charAt(i));
            line2.append(inputArray[1].charAt(i));
            line3.append(inputArray[2].charAt(i));
            line4.append(inputArray[3].charAt(i));
            counter++;

            if (counter == 3) {

                String digit = line1.toString() + "\n"
                        + line2.toString() + "\n"
                        + line3.toString() + "\n"
                        + line4.toString();

                result.add(digit);

                counter = 0;
                line1 = new StringBuilder();
                line2 = new StringBuilder();
                line3 = new StringBuilder();
                line4 = new StringBuilder();
            }
        }
        return result;
    }

    // Method for generating possible account numbers for numbers flagged ILL or
    // ERR.
    private List<String> getPossibleNumbers(String accountNumber, List<String> digits, int index) {
        List<String> numberVariates = variateNumber(digits.get(index));
        List<String> possibleAccountNumbers = new ArrayList<>();
        StringBuilder copyOfAccountNumber;

        for (String s : numberVariates) {
            copyOfAccountNumber = new StringBuilder(accountNumber);
            copyOfAccountNumber.setCharAt(index, s.charAt(0));
            if (checksumChecker(copyOfAccountNumber.toString())) {
                possibleAccountNumbers.add(copyOfAccountNumber.toString());
            }
        }

        return possibleAccountNumbers;
    }

    // Method for testing if adding or removing a pipe or an underscore results
    // in a usable number. Reads the whole string representation of a digit and
    // adds or removes a pipe or an underscore in relevant places. Returns a list
    // of numbers.
    private List<String> variateNumber(String digit) {
        StringBuilder copyOfDigit;
        List<String> result = new ArrayList<>();
        String number;

        for (int i = 0; i < digit.length(); i++) {
            copyOfDigit = new StringBuilder(digit);
            if (i == 1 || i == 5 || i == 9) {
                if (digit.charAt(i) == ' ') {
                    copyOfDigit.setCharAt(i, '_');
                } else {
                    copyOfDigit.setCharAt(i, ' ');
                }
            } else if (i == 4 || i == 6 || i == 8 || i == 10) {
                if (digit.charAt(i) == ' ') {
                    copyOfDigit.setCharAt(i, '|');
                } else {
                    copyOfDigit.setCharAt(i, ' ');
                }
            }
            number = Digits.getDigit(copyOfDigit.toString());

            if (!number.equals("?")) {
                result.add(number);
            }
        }
        return result;
    }
}
