package shop.rule;

import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;

/**
 * @author M.R Khabireh
 * Date: 08/11/2026
 * Time: 10:34
 */
public class BestStudent implements DiscountRule {
    @Override
    public double apply(Product product, Customer customer) {
        return customer.isVip() && customer.isStudent() ? 0.32 :15;
    }
}
