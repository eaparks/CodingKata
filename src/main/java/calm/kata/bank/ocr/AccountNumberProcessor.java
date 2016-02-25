package calm.kata.bank.ocr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Kata from:
 * http://www.codingdojo.org/cgi-bin/index.pl?KataBankOCR
 *
 * Each entry is 4 lines long, and each line has 27 characters.
 * The first 3 lines of each entry contain an account number written using pipes and underscores,
 * and the fourth line is blank.
 *
 * Each account number should have 9 digits, all of which should be in the range 0-9.
 */
public class AccountNumberProcessor {

    private static final Logger logger = LogManager.getLogger(AccountNumberProcessor.class);
    public static final String EMPTY_NUMBER_TOP = "   ";

    protected List<String> readFile(String file) throws IOException, IllegalInputException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String[] fourLines = new String[4];
        List<String> accountNumberList = new ArrayList<>();

        try {
            int i = 0;
            while ((fourLines[i++] = reader.readLine()) != null) {
                logger.debug("Line {}: [{}]", i - 1, fourLines[i - 1]);
                if (i == 4) {
                    accountNumberList.add(processFourLines(fourLines));
                    i = 0;
                }
            }
            if(i != 1) {
                throw new IOException("Improper number of lines. Found " + i + " extra.");
            }
        } finally {
            reader.close();
        }
        return accountNumberList;
    }

    static String anotherReadFile(String path, Charset encoding) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    protected String processFourLines(String[] lines) throws IllegalInputException {

        if(lines.length != 4) {
            throw new IllegalInputException("Input must be 4 lines.");
        }
        AccountNumber accountNumber = new AccountNumber();

        // First line
        StringBuilder[] ocrNumberFromFile = handleFirstLine(lines[0]);
        String line;
        int i;

        // Second line
        line = lines[1];
        for (i = 0; i < 9; i++) {
            ocrNumberFromFile[i].append(line.substring(i * 3, (i + 1) * 3));
        }

        // Third line
        line = lines[2];
        for (i = 0; i < 9; i++) {
            ocrNumberFromFile[i].append(line.substring(i * 3, (i + 1) * 3));
            String completeOcrNum = ocrNumberFromFile[i].toString();
            accountNumber.processSingleOcrNumber(completeOcrNum);
        }

        return accountNumber.processAlternates();
    }

    private StringBuilder[] handleFirstLine(String line1) {

        StringBuilder[] ocrNumberFromFile = new StringBuilder[9];
        for (int i = 0; i < 9; i++) {
            if(line1.length() > 0) {
                ocrNumberFromFile[i] = new StringBuilder(line1.substring(i * 3, (i + 1) * 3));
            } else {
                ocrNumberFromFile[i] = new StringBuilder(EMPTY_NUMBER_TOP);
            }
        }
        
        return ocrNumberFromFile;
    }

    // TODO - change input param to String list
    private void writeResults(List<String> accountNumbers) throws IOException {

        List<String> lines = new ArrayList<>(accountNumbers.size());
        
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        for (String num : accountNumbers)
        {
            // TODO - in AccountNumber
//            if(num.isIllegible()) {
//                numStatus = " ILL";
//            } else if(!num.isValid()) {
//                numStatus = " ERR";
//            }
            writer.println(num);
        }
        writer.close();
        Files.write(Paths.get("file5.txt"), lines, UTF_8);

    }

    public static void main(String[] args) {

        AccountNumberProcessor processor = new AccountNumberProcessor();
        try {
            List<String> accountNumbers = processor.readFile("data/input.txt");
            processor.writeResults(accountNumbers);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalInputException e) {
            e.printStackTrace();
        }
    }
    

}
