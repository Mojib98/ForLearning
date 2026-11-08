package shop.strategy;

import java.util.List;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:33
 */
public class SumDiscounts implements DiscountCombinationStrategy {
    @Override
    public double combine(List<Double> discounts) {
        return Math.min(1.0, discounts.stream()
                .mapToDouble(Double::doubleValue)
                .sum());
    }
}