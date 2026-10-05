package example.bestprise;

import static example.bestprise.Shop.delay;
import static java.lang.String.format;

/**
 * @author M.R Khabireh
 * Date: 12/09/2026
 * Time: 09:33
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }
    private static double apply(double price, Code code) {
        delay();
        return Double.parseDouble(String.format(String.valueOf(price * (100 - code.percentage) / 100)));
    }
}
