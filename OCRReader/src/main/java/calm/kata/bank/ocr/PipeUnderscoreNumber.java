package calm.kata.bank.ocr;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: edward
 * Date: 1/1/16
 * Time: 4:57 PM
 *
 * Each entry is 4 lines long. The first 3 lines of each entry contain an account number written
 * using pipes and underscores, and the fourth line is blank
 */
public enum PipeUnderscoreNumber {

    ONE(    "   " +
            "  |" +
            "  |", "1", new String[]{"SEVEN"}),
    TWO(    " _ " +
            " _|" +
            "|_ ", "2", null),
    THREE(  " _ " +
            " _|" +
            " _|", "3", new String[]{"NINE"}),
    FOUR(   "   " +
            "|_|" +
            "  |", "4", null),
    FIVE(   " _ " +
            "|_ " +
            " _|", "5", new String[]{"SIX", "NINE"}),
    SIX(    " _ " +
            "|_ " +
            "|_|", "6", new String[]{"FIVE", "EIGHT"}),
    SEVEN(  " _ " +
            "  |" +
            "  |", "7", new String[]{"ONE"}),
    EIGHT(  " _ " +
            "|_|" +
            "|_|", "8", new String[]{"ZERO", "SIX", "NINE"}),
    NINE(   " _ " +
            "|_|" +
            " _|", "9", new String[]{"EIGHT", "THREE"}),
    ZERO(   " _ " +
            "| |" +
            "|_|", "0", new String[]{"EIGHT"}),
    ILLEGIBLE( "", "?", null);

    private final String ocrString;
    private final String number;
    private final String[] alternates;

    PipeUnderscoreNumber(String ocrString, String num, String[] alternates) {

        this.ocrString = ocrString;
        this.number = num;
        this.alternates = alternates;
    }

    public String getOcrString() {
        return ocrString;
    }
    
    public String getNumber() {
        return number;
    }

    public String[] getAlternates() {

        return alternates;
    }
    
    public boolean hasAlternates() {
        
        return getAlternates() != null;
    }
    
    public static Set<PipeUnderscoreNumber> findNumbersFromIllegible(String funkyInput) {
        
        Set<PipeUnderscoreNumber> result = new HashSet<>();
        if(!fromOcrString(funkyInput).equals(ILLEGIBLE)) {
            return result;
        }
        String potentialFix;
        
        // look by adding single dash in all legit places if pipe not there
                // underscore can only be in position 1,4,7
        for(int i = 1; i < 10; i = i + 3) {
            if (funkyInput.charAt(i) != '|') {
                potentialFix = funkyInput.substring(0, i) + "_" + funkyInput.substring(i + 1);
                if (!fromOcrString(potentialFix).equals(ILLEGIBLE)) {
                    result.add(fromOcrString(potentialFix));
                }
            }
        }
        
        // look by removing a single underscore
            // substitute space for underscore and test
        for(int i = 0; i < funkyInput.length(); i++) {
            if (funkyInput.charAt(i) == '_') {
                potentialFix = funkyInput.substring(0, i) + " " + funkyInput.substring(i + 1);
                if(!fromOcrString(potentialFix).equals(ILLEGIBLE)) {
                    result.add(fromOcrString(potentialFix));
                }
            }
        }
        
        // look by adding a single pipe in all possible places, but not replacing underscore
            // never a pipe in first 3 chars
        for(int i = 3; i < funkyInput.length(); i++) {
            if (funkyInput.charAt(i) != '_') {
                potentialFix = funkyInput.substring(0, i) + "|" + funkyInput.substring(i + 1);
                if (!fromOcrString(potentialFix).equals(ILLEGIBLE)) {
                    result.add(fromOcrString(potentialFix));
                }
            }
        }
        
        // look by removing a single pipe
            // substitute space for pipe and test
        for(int i = 0; i < funkyInput.length(); i++) {
            if (funkyInput.charAt(i) == '|') {
                potentialFix = funkyInput.substring(0, i) + " " + funkyInput.substring(i + 1);
                if(!fromOcrString(potentialFix).equals(ILLEGIBLE)) {
                    result.add(fromOcrString(potentialFix));
                }
            }
        }
        
        return result;
    }

    @Override
    public String toString() {
        return number;
    }

    public static PipeUnderscoreNumber fromString(String name) {

        return EnumUtil.getEnumFromString(PipeUnderscoreNumber.class, name);
    }

    public static PipeUnderscoreNumber fromOcrString(String text) {

        if (text != null) {
            for (PipeUnderscoreNumber n : PipeUnderscoreNumber.values()) {
                if (text.equalsIgnoreCase(n.ocrString)) {
                    return n;
                }
            }
        }
        return ILLEGIBLE;
    }

    public static PipeUnderscoreNumber fromInt(int num) {

        for (PipeUnderscoreNumber n : PipeUnderscoreNumber.values()) {
            if (String.valueOf(num).equalsIgnoreCase(n.number)) {
                return n;
            }
        }
        return ILLEGIBLE;
    }

    public Set<PipeUnderscoreNumber> getAlternatesAsEnums() {

        Set<PipeUnderscoreNumber> alts = new HashSet<>();
        if (alternates != null) {
            for(String alt : alternates) {
                alts.add(fromString(alt));
            }
        }
        return alts;
    }
}
