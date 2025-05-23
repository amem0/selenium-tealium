package internal.rejon.tealiumdemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public enum Numbers {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE
    }

    final static Pattern pricePattern = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");
    final static Pattern itemCountPattern = Pattern.compile("(\\d+)");

    /**
     * @param priceString, String to be parsed
     * @return price, double.
     * Punojme mbi match e pare. Ka probleme matching edhe pse pattern eshte i sakte???
     */
    public static double priceString2Int(String priceString) {
        Matcher matchPrice = pricePattern.matcher(priceString); //???
        return (double) Double.parseDouble(matchPrice.group());
    }

    /**
     * @param itemCountString, String to be parsed
     * @return itemCount, int.
     * Jo shume i sakte, thjesht merr numrin e pare qe has ne string.
     */
    public static int itemCountString2Int(String itemCountString) {
        Matcher matchItemCount = itemCountPattern.matcher(itemCountString);
        return (int) Integer.parseInt(matchItemCount.group());
    }
}
