package shop.engin;

import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;
import shop.strategy.DiscountCombinationStrategy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:34
 */
public class PriceCalculator {
    private final List<DiscountRule> rules;
    private final DiscountCombinationStrategy combination;

    public PriceCalculator(List<DiscountRule> rules,
                           DiscountCombinationStrategy combination) {
        this.rules = List.copyOf(Objects.requireNonNull(rules, "rules"));
        this.combination = Objects.requireNonNull(combination, "combination");
    }

    public double calculatePrice(Product product, Customer customer) {
        List<Double> appliedDiscounts = rules.stream()
                .map(rule -> rule.apply(product, customer))
                .filter(d -> d > 0)
                .collect(Collectors.toList());

        double totalDiscount = combination.combine(appliedDiscounts);
        return product.getBasePrice() * (1 - totalDiscount);
    }

    /**
     * گزارش کامل تخفیف‌ها (برای دیباگ و نمایش).
     */
    public String explain(Product product, Customer customer) {
        StringBuilder sb = new StringBuilder();
        sb.append("قیمت پایه: ").append(String.format("%.0f", product.getBasePrice())).append("\n");
        double total = 0;
        for (int i = 0; i < rules.size(); i++) {
            double d = rules.get(i).apply(product, customer);
            if (d > 0) {
                sb.append(String.format("  قانون %d: %.0f%%%n", i + 1, d * 100));
                total++;
            }
        }
        if (total == 0) {
            sb.append("  هیچ تخفیفی اعمال نشد.\n");
        }
        double finalPrice = calculatePrice(product, customer);
        double discountPercent = (1 - finalPrice / product.getBasePrice()) * 100;
        sb.append(String.format("قیمت نهایی: %.0f (%.1f%% تخفیف)%n", finalPrice, discountPercent));
        return sb.toString();
    }
}