package shop.rule;

import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:26
 */
public class HighValueDiscount implements DiscountRule {
    private final double threshold;
    private final double rate;

    public HighValueDiscount(double threshold, double rate) {
        this.threshold = threshold;
        this.rate = rate;
    }

    public HighValueDiscount() {
        this(500_000, 0.15);
    }

    @Override
    public double apply(Product product, Customer customer) {
        return product.getBasePrice() > threshold ? rate : 0.0;
    }
}