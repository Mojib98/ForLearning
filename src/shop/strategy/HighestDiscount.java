package shop.strategy;

import java.util.List;
/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:32
 */

public class HighestDiscount implements DiscountCombinationStrategy {
    @Override
    public double combine(List<Double> discounts) {
        return discounts.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);
    }
}