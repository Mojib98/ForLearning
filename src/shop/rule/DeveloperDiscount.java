package shop.rule;

import shop.model.Category;
import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 10:01
 */
public class DeveloperDiscount implements DiscountRule {
    @Override
    public double apply(Product product, Customer customer) {
        if (product.getCategory().equals(Category.ELECTRONICS))
            return 0.27D;
        return 0.15;
    }

    @Override
    public DiscountRule and(DiscountRule other) {
        return (p,c) -> other.apply(p,c) + this.apply(p,c);
    }
}
