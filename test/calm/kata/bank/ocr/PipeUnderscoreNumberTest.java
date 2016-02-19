package calm.kata.bank.ocr;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: edward
 * Date: 1/2/16
 * Time: 11:21 AM
 */
public class PipeUnderscoreNumberTest {

    @Test
    public void testFromString() throws Exception {

        String input =   " _ " +
                         "|_|" +
                         "|_|";

        assertEquals(PipeUnderscoreNumber.EIGHT, PipeUnderscoreNumber.fromOcrString(input));

        input = " _ " +
                "| |" +
                "|_|";

        assertEquals(PipeUnderscoreNumber.ZERO, PipeUnderscoreNumber.fromOcrString(input));

        input = " _ | ||_|";
        assertEquals(PipeUnderscoreNumber.ZERO, PipeUnderscoreNumber.fromOcrString(input));
    }

    @Test
    public void testFromStringForOne() throws Exception {

        String input =  "   " +
                        " | " +
                        " | ";

        assertEquals(PipeUnderscoreNumber.ONE, PipeUnderscoreNumber.fromOcrString(input));
    }

    @Test
    public void testFromIntForOne() throws Exception {

        assertEquals(PipeUnderscoreNumber.ZERO, PipeUnderscoreNumber.fromInt(0));
        assertEquals(PipeUnderscoreNumber.ONE, PipeUnderscoreNumber.fromInt(1));
        assertEquals(PipeUnderscoreNumber.TWO, PipeUnderscoreNumber.fromInt(2));
        assertEquals(PipeUnderscoreNumber.THREE, PipeUnderscoreNumber.fromInt(3));
        assertEquals(PipeUnderscoreNumber.FOUR, PipeUnderscoreNumber.fromInt(4));
        assertEquals(PipeUnderscoreNumber.FIVE, PipeUnderscoreNumber.fromInt(5));
        assertEquals(PipeUnderscoreNumber.SIX, PipeUnderscoreNumber.fromInt(6));
        assertEquals(PipeUnderscoreNumber.SEVEN, PipeUnderscoreNumber.fromInt(7));
        assertEquals(PipeUnderscoreNumber.EIGHT, PipeUnderscoreNumber.fromInt(8));
        assertEquals(PipeUnderscoreNumber.NINE, PipeUnderscoreNumber.fromInt(9));
    }

    @Test
    public void testGetOcrString() throws Exception {

        String expected =   " _ " +
                            "|_|" +
                            "|_|";

        assertEquals(expected, PipeUnderscoreNumber.EIGHT.getOcrString());
    }

    @Test
    public void testToString() throws Exception {

        assertEquals("8", PipeUnderscoreNumber.EIGHT.toString());
    }
}