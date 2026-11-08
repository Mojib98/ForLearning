package shop.rule;

import shop.model.Category;
import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:25
 */

public class ElectronicsDiscount implements DiscountRule {
    private final double rate;

    public ElectronicsDiscount(double rate) {
        this.rate = rate;
    }

    public ElectronicsDiscount() {
        this(0.10);
    }

    @Override
    public double apply(Product product, Customer customer) {
        return product.getCategory() == Category.ELECTRONICS ? rate : 0.0;
    }
}
