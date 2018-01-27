package calm.kata.bank.ocr;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: edward
 * Date: 1/1/16
 * Time: 10:16 PM
 */
public class AccountNumberProcessorTest {

    AccountNumberProcessor unit;

    @Before
    public void setUp() throws Exception {

        unit = new AccountNumberProcessor();
    }

    @Test
    public void testProcessFourLines() throws Exception {

        String[] lines = new String[4];
        lines[0] = "    _  _     _  _  _  _  _ ";
        lines[1] = "  | _| _||_||_ |_   ||_||_|";
        lines[2] = "  ||_  _|  | _||_|  ||_| _|";
        lines[3] = "";
        assertEquals("123456789", unit.processFourLines(lines));
        
        String[] badLines = new String[3];
        try {
            unit.processFourLines(badLines);
            fail("Was expecting an IllegalInputException");
        } catch (IllegalInputException iie) {
            // expect to get here
        }
    }

    @Test
    public void testReadOnesFile() throws Exception {

        AccountNumberProcessor main = new AccountNumberProcessor();
        List<String> accountNumberList = main.readFile("data/ones.txt");

        assertNotNull(accountNumberList);
        assertEquals(1, accountNumberList.size());
        assertEquals(111111111, Integer.parseInt(accountNumberList.get(0).toString()));
    }

    @Test
    public void testReadFile() throws Exception {

        AccountNumberProcessor main = new AccountNumberProcessor();
        List<String> accountNumberList = main.readFile("data/input.txt");

        assertNotNull(accountNumberList);
        System.out.println(accountNumberList);
    }

    @Test
    public void testReadFileWithMalformedNumber() throws Exception {

        AccountNumberProcessor main = new AccountNumberProcessor();
        List<String> accountNumberList = main.readFile("data/malformed_1.txt");

        assertNotNull(accountNumberList);
        assertEquals(1, accountNumberList.size());
        assertEquals(123456789, Integer.parseInt(accountNumberList.get(0).toString()));
        System.out.println(accountNumberList);
    }
}