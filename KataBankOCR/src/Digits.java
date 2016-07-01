/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antti
 */
public final class Digits {
    
    public static final String ZERO = " _ \n" +
                                      "| |\n" +
                                      "|_|\n" +
                                      "   ";
    
    public static final String ONE = "   \n" +
                                     "  |\n" +
                                     "  |\n" +
                                     "   ";
    
    public static final String TWO = " _ \n" +
                                     " _|\n" +
                                     "|_ \n" +
                                     "   ";
    
    public static final String THREE = " _ \n" +
                                       " _|\n" +
                                       " _|\n" +
                                       "   ";
    
    public static final String FOUR = "   \n" +
                                      "|_|\n" +
                                      "  |\n" +
                                      "   ";
    
    public static final String FIVE = " _ \n" +
                                      "|_ \n" +
                                      " _|\n" +
                                      "   ";
    
    public static final String SIX = " _ \n" +
                                     "|_ \n" +
                                     "|_|\n" +
                                     "   ";
    
    public static final String SEVEN = " _ \n" +
                                       "  |\n" +
                                       "  |\n" +
                                       "   ";
    
    public static final String EIGHT = " _ \n" +
                                       "|_|\n" +
                                       "|_|\n" +
                                       "   ";
    
    public static final String NINE = " _ \n" +
                                      "|_|\n" +
                                      " _|\n" +
                                      "   ";
    
    public static String getDigit(String digit) {
        switch (digit) {
            case Digits.ZERO: return "0";
            case Digits.ONE: return "1";
            case Digits.TWO: return "2";
            case Digits.THREE: return "3";
            case Digits.FOUR: return "4";
            case Digits.FIVE: return "5";
            case Digits.SIX: return "6";
            case Digits.SEVEN: return "7";
            case Digits.EIGHT: return "8";
            case Digits.NINE: return "9";
            default: return "?";
        }
    }
}
