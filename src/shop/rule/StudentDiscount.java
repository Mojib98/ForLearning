package shop.rule;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:28
 */

import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;

/**
 * قانون جدید که بعداً اضافه شد — بدون تغییر در PriceCalculator.
 * فقط دانشجویانی که بیش از ۱ سال عضو هستند.
 */
public class StudentDiscount implements DiscountRule {
    private final int minMonths;
    private final double rate;

    public StudentDiscount(int minMonths, double rate) {
        this.minMonths = minMonths;
        this.rate = rate;
    }

    public StudentDiscount() {
        this(12, 0.07);
    }

    @Override
    public double apply(Product product, Customer customer) {
        return (customer.isStudent() && customer.getMembershipMonths() >= minMonths)
                ? rate : 0.0;
    }
}