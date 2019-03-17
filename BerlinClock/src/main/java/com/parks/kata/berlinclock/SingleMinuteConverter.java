package com.parks.kata.berlinclock;

import org.apache.commons.lang3.StringUtils;

public class SingleMinuteConverter {

    static final String[] BERLIN_MINUTES = {"OOOO", "YOOO", "YYOO", "YYYO", "YYYY"};

    /**
     * The final two rows represent the minutes.
     *
     * The upper row represents 5 minute blocks, and is made up of 11 lamps-
     * every third lamp is red, the rest are yellow.
     *
     * The bottom row represents 1 minute blocks, and is made up of 4 yellow lamps.
     *
     * @param digitalTime - in the format "hh:mm:ss"
     * @return
     */
    String minutesInBerlinTime(String digitalTime) {

        String digitalMinutes = StringUtils.substringBetween(digitalTime, ":");

        String berlinSingleMinutes;

        if(isEvenlyDivisibleByFive(digitalMinutes)) {
            berlinSingleMinutes = BERLIN_MINUTES[0];
        } else {
            berlinSingleMinutes = BERLIN_MINUTES[Integer.parseInt(digitalMinutes) % 5];
        }

        return berlinSingleMinutes;
    }

    boolean isEvenlyDivisibleByFive(String minutesInDigitalFormat) {

        if(StringUtils.isBlank(minutesInDigitalFormat)) {
            throw new RuntimeException("Digital minutes cannot be blank.");
        }

        return minutesInDigitalFormat.endsWith("0") || minutesInDigitalFormat.endsWith("5");
    }
}
