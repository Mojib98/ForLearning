package shop.rule;

import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:27
 */
public class BlackFridayDiscount implements DiscountRule {
    private final double rate;

    public BlackFridayDiscount(double rate) {
        this.rate = rate;
    }

    public BlackFridayDiscount() {
        this(0.20);
    }

    @Override
    public double apply(Product product, Customer customer) {
        return rate;
    }
}
