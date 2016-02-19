package calm.kata.bank.ocr;

/**
 * Created with IntelliJ IDEA.
 * User: edward
 * Date: 1/2/16
 * Time: 11:54 AM
 */
public class EnumUtil {

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {

        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
                // empty
            }
        }
        return null;
    }

}
