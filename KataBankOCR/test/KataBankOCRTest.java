import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antti
 */
public class KataBankOCRTest {
    
    private KataBankOCR ocr;
    
    public KataBankOCRTest() {
    }
    
    @Before
    public void setUp() {
        ocr = new KataBankOCR();
    }
    
    // First tests are for checking that the numbers are read correctly
    
    @Test
    public void allZeroes() {
        String zeroes = " _  _  _  _  _  _  _  _  _ " + "\n" +
                        "| || || || || || || || || |" + "\n" +
                        "|_||_||_||_||_||_||_||_||_|" + "\n" +
                        "                           ";

        assertEquals("000000000", ocr.readToNumber(zeroes));
    }
    
    @Test
    public void allOnes() {
        String ones = "                           " + "\n" +
                      "  |  |  |  |  |  |  |  |  |" + "\n" +
                      "  |  |  |  |  |  |  |  |  |" + "\n" +
                      "                           ";

        assertEquals("111111111", ocr.readToNumber(ones));
    }
    
    @Test
    public void allTwos() {
        String twos = " _  _  _  _  _  _  _  _  _ \n" +
                      " _| _| _| _| _| _| _| _| _|\n" +
                      "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                      "                           ";

        assertEquals("2222222222", ocr.readToNumber(twos));
    }
    
    @Test
    public void allThrees() {
        String threes = " _  _  _  _  _  _  _  _  _ \n" +
                        " _| _| _| _| _| _| _| _| _|\n" +
                        " _| _| _| _| _| _| _| _| _|\n" +
                        "                           ";

        assertEquals("333333333", ocr.readToNumber(threes));
    }
    
    @Test
    public void allFours() {
        String fours = "                           \n" +
                       "|_||_||_||_||_||_||_||_||_|\n" +
                       "  |  |  |  |  |  |  |  |  |\n" +
                       "                           ";

        assertEquals("444444444", ocr.readToNumber(fours));
    }
    
    @Test
    public void allFives() {
        String fives = " _  _  _  _  _  _  _  _  _ \n" +
                       "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                       " _| _| _| _| _| _| _| _| _|\n" +
                       "                           ";

        assertEquals("555555555", ocr.readToNumber(fives));
    }
    
    @Test
    public void allSixes() {
        String sixes = " _  _  _  _  _  _  _  _  _ \n" +
                       "|_ |_ |_ |_ |_ |_ |_ |_ |_ \n" +
                       "|_||_||_||_||_||_||_||_||_|\n" +
                       "                           ";

        assertEquals("666666666", ocr.readToNumber(sixes));
    }
    
    @Test
    public void allSevens() {
        String sevens = " _  _  _  _  _  _  _  _  _ \n" +
                        "  |  |  |  |  |  |  |  |  |\n" +
                        "  |  |  |  |  |  |  |  |  |\n" +
                        "                           ";

        assertEquals("777777777", ocr.readToNumber(sevens));
    }
    
    @Test
    public void allEights() {
        String eights = " _  _  _  _  _  _  _  _  _ \n" +
                        "|_||_||_||_||_||_||_||_||_|\n" +
                        "|_||_||_||_||_||_||_||_||_|\n" +
                        "                           ";

        assertEquals("888888888", ocr.readToNumber(eights));
    }
    
    @Test
    public void allNines() {
        String nines = " _  _  _  _  _  _  _  _  _ \n" +
                       "|_||_||_||_||_||_||_||_||_|\n" +
                       " _| _| _| _| _| _| _| _| _|\n" +
                       "                           ";

        assertEquals("999999999", ocr.readToNumber(nines));
    }
    
    // Next we test that the input account numbers are valid.
    
    @Test
    public void isValidAccountNumber1() {
        String nmbr =  " _  _  _  _  _  _  _  _    \n" +    
                       "| || || || || || || ||_   |\n" +
                       "|_||_||_||_||_||_||_| _|  |\n" +
                       "                           ";
        assertEquals("000000051", ocr.readAccountNumber(nmbr));
    }
    
    @Test
    public void isValidAccountNumber2() {
        String nmbr = "    _  _  _  _  _  _     _ \n" +
                      "|_||_|| || ||_   |  |  | _ \n" +
                      "  | _||_||_||_|  |  |  | _|\n" +
                      "                           ";
        
        assertEquals("49006771? ILL", ocr.readAccountNumber(nmbr));
    }
    
    @Test
    public void isValidAccountNumber3() {
        String nmbr = "    _  _     _  _  _  _  _ \n" +
                      "  | _| _||_| _ |_   ||_||_|\n" +
                      "  ||_  _|  | _||_|  ||_| _ \n" +
                      "                           ";
        
        assertEquals("1234?678? ILL", ocr.readAccountNumber(nmbr));
    }
    
    @Test
    public void isValidAccountNumber4() {
        String nmbr = " _  _     _  _        _  _ \n" +
                      "|_ |_ |_| _|  |  ||_||_||_ \n" +
                      "|_||_|  | _|  |  |  | _| _|\n" +
                      "                           ";
        
        assertEquals("664371495 ERR", ocr.readAccountNumber(nmbr));
    }
    
    // Finally we test resolving correct account numbers from incomplete
    // or erroneous.
    
    @Test
    public void resolvedAccountNumber1() {
        String nmbr = "                           " + "\n" +
                      "  |  |  |  |  |  |  |  |  |" + "\n" +
                      "  |  |  |  |  |  |  |  |  |" + "\n" +
                      "                           ";
        
        assertEquals("711111111", ocr.resolveAccountNumber(nmbr));
    }
    
    @Test
    public void resolvedAccountNumber2() {
        String nmbr = " _  _  _  _  _  _  _  _  _ \n" +
                      "  |  |  |  |  |  |  |  |  |\n" +
                      "  |  |  |  |  |  |  |  |  |\n" +
                      "                           ";
        
        assertEquals("777777177", ocr.resolveAccountNumber(nmbr));
    }
    
    @Test
    public void resolvedAccountNumber3() {
        String nmbr = " _  _  _  _  _  _  _  _  _ \n" +
                      " _|| || || || || || || || |\n" +
                      "|_ |_||_||_||_||_||_||_||_|\n" +
                      "                           ";
        
        assertEquals("200800000", ocr.resolveAccountNumber(nmbr));
    }
    
    @Test
    public void resolvedAccountNumber4() {
        String nmbr = " _  _  _  _  _  _  _  _  _ \n" +
                      " _| _| _| _| _| _| _| _| _|\n" +
                      " _| _| _| _| _| _| _| _| _|\n" +
                      "                           ";
        
        assertEquals("333393333", ocr.resolveAccountNumber(nmbr));
    }
    
    @Test
    public void resolvedAccountNumber5() {
        String nmbr = " _  _  _  _  _  _  _  _  _ \n" +
                      "|_||_||_||_||_||_||_||_||_|\n" +
                      "|_||_||_||_||_||_||_||_||_|\n" +
                      "                           ";
        
        assertEquals("888888888 AMB", ocr.resolveAccountNumber(nmbr));
    }
    
    @Test
    public void resolvedAccountNumber6() {
        String nmbr = "    _  _  _  _  _  _     _ \n" +
                      "|_||_|| ||_||_   |  |  | _ \n" +
                      "  | _||_||_||_|  |  |  | _|\n" +
                      "                           ";
        
        assertEquals("490867715", ocr.resolveAccountNumber(nmbr));
    }
    
    @Test
    public void resolvedAccountNumber7() {
        String nmbr = " _     _  _  _  _  _  _    \n" +
                      "| || || || || || || ||_   |\n" +
                      "|_||_||_||_||_||_||_| _|  |\n" +
                      "                           ";
        
        assertEquals("000000051", ocr.resolveAccountNumber(nmbr));
    }
    
    @Test
    public void resolvedAccountNumber8() {
        String nmbr = " _  _  _  _  _  _  _  _  _ \n" +
                      "|_||_||_||_||_||_||_||_||_|\n" +
                      " _| _| _| _| _| _| _| _| _|\n" +
                      "                           ";
        
        assertEquals("999999999 AMB", ocr.resolveAccountNumber(nmbr));
    }

}
