package shop.strategy;

import java.util.List;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:31
 */
@FunctionalInterface
public interface DiscountCombinationStrategy {
    /**
     * لیست تخفیف‌ها (هر کدام بین 0.0 تا 1.0) را ترکیب می‌کند
     * و یک تخفیف نهایی برمی‌گرداند.
     */
    double combine(List<Double> discounts);
}