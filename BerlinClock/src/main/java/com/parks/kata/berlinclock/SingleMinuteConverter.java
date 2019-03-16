package com.parks.kata.berlinclock;

public class SingleMinuteConverter {

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

        String digitalMinutes = digitalTime.substring(digitalTime.lastIndexOf(':'));

        String berlinSingleMinutes = null;

        if(digitalMinutes.endsWith("0")) {
            berlinSingleMinutes = "OOOO";
        }

        return berlinSingleMinutes;
    }
}
