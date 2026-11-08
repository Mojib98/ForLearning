package shop.model;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:23
 */

/**
 * یک قانون تخفیف. مقدار برگشتی بین 0.0 تا 1.0 است.
 * مثلاً 0.10 یعنی ۱۰٪ تخفیف.
 */
@FunctionalInterface
public interface DiscountRule {

    double apply(Product product, Customer customer);

    /**
     * ترکیب با یک قانون دیگر: بالاترین تخفیف بین دو قانون.
     */
    default DiscountRule and(DiscountRule other) {
        return (p, c) -> Math.max(this.apply(p, c), other.apply(p, c));
    }

    /**
     * ترکیب با یک قانون دیگر: جمع دو تخفیف (تا سقف ۱.۰).
     */
    default DiscountRule or(DiscountRule other) {
        return (p, c) -> Math.min(1.0, this.apply(p, c) + other.apply(p, c));
    }

    /**
     * قانون بدون تخفیف (Identity).
     */
    static DiscountRule none() {
        return (p, c) -> 0.0;
    }
}