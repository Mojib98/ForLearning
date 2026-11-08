package shop.model.rule;

import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:26
 */
public class VipDiscount implements DiscountRule {
    private final double rate;

    public VipDiscount(double rate) {
        this.rate = rate;
    }

    public VipDiscount() {
        this(0.05);
    }

    @Override
    public double apply(Product product, Customer customer) {
        return customer.isVip() ? rate : 0.0;
    }
}