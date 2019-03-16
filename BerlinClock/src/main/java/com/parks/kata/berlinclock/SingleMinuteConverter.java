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

        String digitalMinutes = digitalTime.substring(digitalTime.lastIndexOf(':') - 2, digitalTime.lastIndexOf(':'));

        String berlinSingleMinutes = null;

        if(isEvenlyDivisibleByFive(digitalMinutes)) {
            berlinSingleMinutes = "OOOO";
        } else {
            int minutes = Integer.parseInt(digitalMinutes);
            if(minutes % 5 == 1) {
                berlinSingleMinutes = "YOOO";
            } else if(minutes % 5 == 2) {
                berlinSingleMinutes = "YYOO";
            } else if(minutes % 5 == 3) {
                berlinSingleMinutes = "YYYO";
            } else if(minutes % 5 == 4) {
                berlinSingleMinutes = "YYYY";
            }
        }

        return berlinSingleMinutes;
    }

    public boolean isEvenlyDivisibleByFive(String minutesInDigitalFormat) {

        return minutesInDigitalFormat.endsWith("0") || minutesInDigitalFormat.endsWith("5");
    }
}
