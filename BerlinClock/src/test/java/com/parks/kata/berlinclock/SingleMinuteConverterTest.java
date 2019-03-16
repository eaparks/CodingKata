package com.parks.kata.berlinclock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingleMinuteConverterTest {

    SingleMinuteConverter unit;

    /**
     * Test cases:
     * Time	     Row
     * 00:00:00	OOOO
     * 23:59:59	YYYY
     * 12:32:00	YYOO
     * 12:34:00	YYYY
     * 12:35:00	OOOO
     */

    @Before
    public void setup() {
        unit = new SingleMinuteConverter();
    }

    @Test
    public void minutesInBerlinTime() {

        assertEquals("OOOO", unit.minutesInBerlinTime("00:00:00"));

    }
}