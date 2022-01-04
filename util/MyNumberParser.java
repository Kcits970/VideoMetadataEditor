package util;

import java.math.BigInteger;
import java.util.OptionalInt;

public class MyNumberParser {
    public static final BigInteger MAX_INT = BigInteger.valueOf(Integer.MAX_VALUE);
    public static final BigInteger MIN_INT = BigInteger.valueOf(Integer.MIN_VALUE);

    public static OptionalInt parseOptionalInt(String s) {
        try {
            BigInteger integer = new BigInteger(s);
            if (integer.compareTo(MAX_INT) > 0)
                integer = MAX_INT;
            else if (integer.compareTo(MIN_INT) < 0)
                integer = MIN_INT;

            return OptionalInt.of(integer.intValue());
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }
}
