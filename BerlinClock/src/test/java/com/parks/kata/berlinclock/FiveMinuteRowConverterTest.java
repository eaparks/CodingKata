package com.parks.kata.berlinclock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FiveMinuteRowConverterTest {

    MinuteConverter unit;



    @Before
    public void setup() {
        unit = new MinuteConverter();
    }

    @Test
    public void minutesInBerlinTime() {

        assertEquals("OOOO", unit.minutesInBerlinTime("00:00:00"));
        assertEquals("YYYY", unit.minutesInBerlinTime("23:59:11"));
        assertEquals("YYOO", unit.minutesInBerlinTime("12:32:59"));
        assertEquals("YYYY", unit.minutesInBerlinTime("12:34:00"));
        assertEquals("OOOO", unit.minutesInBerlinTime("12:35:00"));
    }

    @Test
    public void isEvenlyDivisibleByFive() {

        String[] yes = {"15", "05", "00", "55"};
        for (String yes1 : yes) {
            assert (unit.isEvenlyDivisibleByFive(yes1));
        }

        String[] notDivisible_1 = {"33", "02"};
        for (String s : notDivisible_1) {
            assertFalse ("This should NOT be evenly divisible by five: " + s, unit.isEvenlyDivisibleByFive(s));
        }

        String[] notDivisible_2 = {null, ""};
        for (String s : notDivisible_2) {
            try {
                unit.isEvenlyDivisibleByFive(s);
                fail ("Bad input <" + s + "> should have thrown RuntimeException.");
            } catch (RuntimeException re) {
                // empty on purpose
            }

        }

    }
}