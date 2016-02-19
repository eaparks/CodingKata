package calm.kata.bank.ocr;

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
            " | " +
            " | ", "1", new String[]{"SEVEN"}),
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

    @Override
    public String toString() {
        return number;
    }

    public static PipeUnderscoreNumber fromString(String name) {

        PipeUnderscoreNumber result = EnumUtil.getEnumFromString(PipeUnderscoreNumber.class, name);
        
        return result;
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

}
