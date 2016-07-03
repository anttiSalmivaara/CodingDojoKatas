package AS.KataBankOCR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Antti
 */
public class main {
    
    public static void main(String[] args) throws IOException {
        KataBankOCR ocr = new KataBankOCR();
        
        File inputFile = new File(args[0]);
        File outputFile = new File("accountnumbers.txt");
        
        Scanner scanner = new Scanner(inputFile);
        
        FileWriter writer = new FileWriter(outputFile);
        try (BufferedWriter bwriter = new BufferedWriter(writer)) {
            StringBuilder sb;
            
            while (scanner.hasNext()) {
                
                sb = new StringBuilder();
                
                sb.append(scanner.nextLine());
                sb.append("\n");
                sb.append(scanner.nextLine());
                sb.append("\n");
                sb.append(scanner.nextLine());
                sb.append("\n");
                sb.append(scanner.nextLine());
                
                bwriter.write(ocr.resolveAccountNumber(sb.toString()));
                bwriter.newLine();
            }
        }
    }
}
