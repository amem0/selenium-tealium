package internal.rejon.tealiumdemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public enum Numbers {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
    }

    public final static Pattern pricePattern = Pattern.compile("\"(\\d+\\.\\d+)|(\\d+)\"g");

    public static double priceString2Int(String priceString) {
        Matcher matchPrice = pricePattern.matcher(priceString);
        return (double) Double.parseDouble(matchPrice.group());
    }
}
