package shop.model;

import java.util.Objects;

/**
 * @author M.R Khabireh
 * Date: 02/11/2026
 * Time: 09:22
 */
public class Product {
    private final String id;
    private final String name;
    private final Category category;
    private final double basePrice;

    public Product(String id, String name, Category category, double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative");
        }
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.category = Objects.requireNonNull(category, "category");
        this.basePrice = basePrice;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public double getBasePrice() { return basePrice; }

    @Override
    public String toString() {
        return String.format("Product[%s, %s, %s, %.0f]",
                id, name, category, basePrice);
    }
}
