package calm.kata.bank.ocr;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: edward
 * Date: 1/1/16
 * Time: 9:01 PM
 */
public class AccountNumberTest {

    private AccountNumber unit;

    @Before
    public void setUp() throws Exception {
        unit = new AccountNumber();
    }
    
    @Test
    public void testConstructWithInt() {
        
        int num = 123456789;
        unit = new AccountNumber(num);
        
        assertFalse(unit.isPartial());
        assertEquals("123456789", unit.toString());
    }

    @Test
    public void testAppendDigit() throws Exception {

        assertTrue(unit.appendDigit(PipeUnderscoreNumber.EIGHT));
        assertTrue(unit.appendDigit(PipeUnderscoreNumber.SIX));

        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);

        assertFalse(unit.appendDigit(PipeUnderscoreNumber.EIGHT));
        assertFalse(unit.appendDigit(null));
    }

    @Test
    public void testToString() throws Exception {

        assertTrue(unit.appendDigit(PipeUnderscoreNumber.EIGHT));
        assertTrue(unit.appendDigit(PipeUnderscoreNumber.SIX));

        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.FOUR);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);

        assertEquals("866664666", unit.toString());

        unit = new AccountNumber();
        unit.appendDigit(PipeUnderscoreNumber.EIGHT);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.ILLEGIBLE);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.FOUR);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        unit.appendDigit(PipeUnderscoreNumber.SIX);
        
        assertTrue(unit.toString().contains("?"));
    }
    
    @Test
    public void testIsValid() throws Exception {
        
        int num = 123452789;
        unit = new AccountNumber(num);
        assertFalse(unit.isValid());

        unit = new AccountNumber(345882865);
        assertTrue(unit.isValid());
        
        num = 171711177;
        unit = new AccountNumber(num);
        assertFalse(unit.isValid());
        
        num = 777777177;
        unit = new AccountNumber(num);
        assertTrue(unit.isValid());
    }
    
    // Yo EDWARD - this is a test to see how merging works with Git.
    
    @Test
    public void testFindAlternate() throws Exception {
        
        /*
         _  _  _  _  _  _  _  _  _ 
          |  |  |  |  |  |  |  |  |
          |  |  |  |  |  |  |  |  |
                                   
                alt should be 777777177
         */

        unit = new AccountNumber(777777777);
        AccountNumber alt = unit.findSingleSubstitutionAlternate();
        assertNotNull(alt);
        assertEquals(777777177, alt.toInt());
        
        /*
          |  |  |  |  |  |  |  |  |
          |  |  |  |  |  |  |  |  |
                                   
            => 711111111
         */
        unit = new AccountNumber(111111111);
        alt = unit.findSingleSubstitutionAlternate();
        assertNotNull(alt);
        assertEquals(711111111, alt.toInt());

        /*
             _  _  _  _  _  _  _  _  _ 
             _|| || || || || || || || |
            |_ |_||_||_||_||_||_||_||_|
                                       
            => 200800000
            */
        unit = new AccountNumber(200000000);
        alt = unit.findSingleSubstitutionAlternate();
        assertNotNull(alt);
        assertEquals(200800000, alt.toInt());
        /*
             _  _  _  _  _  _  _  _  _ 
             _| _| _| _| _| _| _| _| _|
             _| _| _| _| _| _| _| _| _|
                                       
            => 333393333
            */
        unit = new AccountNumber(333333333);
        alt = unit.findSingleSubstitutionAlternate();
        assertNotNull(alt);
        assertEquals(333393333, alt.toInt());

    }

    @Test
    public void testAmbiguous() throws Exception {
        /*
             _  _  _  _  _  _  _  _  _ 
            |_||_||_||_||_||_||_||_||_|
            |_||_||_||_||_||_||_||_||_|
                                       
            => 888888888 AMB ['888886888', '888888880', '888888988']
            */
        unit = new AccountNumber(888888888);
        AccountNumber alt = unit.findSingleSubstitutionAlternate();
        assertNull(alt);
        assertTrue(unit.isAmbiguous());
        
        /*
             _  _  _  _  _  _  _  _  _ 
            |_ |_ |_ |_ |_ |_ |_ |_ |_ 
             _| _| _| _| _| _| _| _| _|
                                       
            => 555555555 AMB ['555655555', '559555555']
            */
        unit = new AccountNumber(555555555);
        alt = unit.findSingleSubstitutionAlternate();
        assertNull(alt);
        assertTrue(unit.isAmbiguous());
        
        /*
             _  _  _  _  _  _  _  _  _ 
            |_ |_ |_ |_ |_ |_ |_ |_ |_ 
            |_||_||_||_||_||_||_||_||_|
                                       
            => 666666666 AMB ['666566666', '686666666']
        */
        unit = new AccountNumber(666666666);
        alt = unit.findSingleSubstitutionAlternate();
        assertNull(alt);
        assertTrue(unit.isAmbiguous());
        
        /*
             _  _  _  _  _  _  _  _  _ 
            |_||_||_||_||_||_||_||_||_|
             _| _| _| _| _| _| _| _| _|
                                       
            => 999999999 AMB ['899999999', '993999999', '999959999']
        */
        unit = new AccountNumber(999999999);
        alt = unit.findSingleSubstitutionAlternate();
        assertNull(alt);
        assertTrue(unit.isAmbiguous());
        
        /*
                _  _  _  _  _  _     _ 
            |_||_|| || ||_   |  |  ||_ 
              | _||_||_||_|  |  |  | _|
                                       
            => 490067715 AMB ['490067115', '490067719', '490867715']
            */
        unit = new AccountNumber(490067715);
        alt = unit.findSingleSubstitutionAlternate();
        assertNull(alt);
        assertTrue(unit.isAmbiguous());

    }
    
    @Test
    public void testMissingPipeOrUnderscore() throws Exception {
        /*
                _  _     _  _  _  _  _ 
             _| _| _||_||_ |_   ||_||_|
              ||_  _|  | _||_|  ||_| _| 
                                       
            => 123456789
        */
        
        // TODO
        
        unit = new AccountNumber(777777777);
        AccountNumber alt = unit.findSingleSubstitutionAlternate();
        assertNotNull(alt);
        assertEquals(777777177, alt.toInt());
        
        /*
             _     _  _  _  _  _  _    
            | || || || || || || ||_   |
            |_||_||_||_||_||_||_| _|  |
                                       
            => 000000051
                _  _  _  _  _  _     _ 
            |_||_|| ||_||_   |  |  | _ 
              | _||_||_||_|  |  |  | _|
                                       
            => 490867715 

         */
        
    }
}