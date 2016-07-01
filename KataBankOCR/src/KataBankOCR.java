/**
 *
 * @author Antti
 */
public class KataBankOCR {
    
    // The first method implements the User Story 1 of the Kata.
    
    public String readToNumber(String input) {
        
        // Chop every line in to its own String.
        String[] inputArray = input.split("\n");
        
        // Build the digit-to-be-read line at a time.
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        StringBuilder line4 = new StringBuilder();
        
        // Accumulate the result one digit at a time.
        StringBuilder result = new StringBuilder();
        
        // Read three characters at a time, then combine and match them with the
        // digit constants in the Digits class.
        int counter = 0;
        for (int i = 0; i < 27; i++) {
            line1.append(inputArray[0].charAt(i));
            line2.append(inputArray[1].charAt(i));
            line3.append(inputArray[2].charAt(i));
            line4.append(inputArray[3].charAt(i)); 
            counter++;
            
            if (counter == 3) {
                
                String digit = line1.toString() + "\n" +
                               line2.toString() + "\n" +
                               line3.toString() + "\n" +
                               line4.toString();
                
                result.append(Digits.getDigit(digit));
                
                counter = 0;
                line1 = new StringBuilder();
                line2 = new StringBuilder();
                line3 = new StringBuilder();
                line4 = new StringBuilder();
            }
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
    // Investigates only the flagged numbers.
    
    public String resolveAccountNumber(String input) {
        return "";
    }
    
    
    
}
