
import shop.engine.PriceCalculator;
import shop.model.Category;
import shop.model.Customer;
import shop.model.DiscountRule;
import shop.model.Product;
import shop.rule.*;
import shop.strategy.HighestDiscount;
import shop.strategy.SumDiscounts;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // ===== داده‌های نمونه =====
        Product laptop = new Product("P1", "لپ‌تاپ", Category.ELECTRONICS, 600_000);
        Product tShirt = new Product("P2", "تی‌شرت", Category.CLOTHING, 200_000);
        Product book = new Product("P3", "کتاب", Category.BOOKS, 100_000);

        Customer vip = new Customer("C1", "علی", true, false, 24);
        Customer vip1 = new Customer("C2", "مجیب", true, false, 24);
        Customer normal = new Customer("C2", "سارا", false, false, 6);
        Customer student = new Customer("C3", "رضا", false, true, 18);

        // ===== قوانین با لامبدا (نسخه‌ی ۴) =====
        DiscountRule electronics = (p, c) ->
                p.getCategory() == Category.ELECTRONICS ? 0.10 : 0.0;
        DiscountRule highValue = (p, c) ->
                p.getBasePrice() > 500_000 ? 0.15 : 0.0;
        DiscountRule vipRule = (p, c) ->
                c.isVip() ? 0.05 : 0.0;
        DiscountRule studentRule =
                (p, c) -> c.isStudent() && c.getMembershipMonths() >= 12 ? 0.07 : 0.0;

        System.out.println("═══════════════════════════════════════════");
        System.out.println("سناریوی ۱: بالاترین تخفیف (قانون ۴)");
        System.out.println("═══════════════════════════════════════════");

        PriceCalculator highestCalc = new PriceCalculator(
                List.of(electronics, highValue, vipRule),
                new HighestDiscount()
        );

        System.out.println("محصول: " + laptop + " | مشتری: " + vip);
        System.out.println(highestCalc.explain(laptop, vip));

        System.out.println("═══════════════════════════════════════════");
        System.out.println("سناریوی ۲: جمع تخفیف‌ها");
        System.out.println("═══════════════════════════════════════════");

        PriceCalculator sumCalc = new PriceCalculator(
                List.of(electronics, highValue, vipRule),
                new SumDiscounts()
        );
        System.out.println("محصول: " + laptop + " | مشتری: " + vip);
        System.out.println(sumCalc.explain(laptop, vip));

        System.out.println("═══════════════════════════════════════════");
        System.out.println("سناریوی ۳: بلک‌فرایدی (قانون جدید بدون تغییر موتور)");
        System.out.println("═══════════════════════════════════════════");

        PriceCalculator blackFridayCalc = new PriceCalculator(
                List.of(new BlackFridayDiscount(0.20)),
                new HighestDiscount()
        );
        System.out.println("محصول: " + tShirt + " | مشتری: " + normal);
        System.out.println(blackFridayCalc.explain(tShirt, normal));

        System.out.println("═══════════════════════════════════════════");
        System.out.println("سناریوی ۴: قانون دانشجویی جدید");
        System.out.println("═══════════════════════════════════════════");

        PriceCalculator studentCalc = new PriceCalculator(
                List.of(studentRule, vipRule),
                new SumDiscounts()
        );
        System.out.println("محصول: " + book + " | مشتری: " + student);
        System.out.println(studentCalc.explain(book, student));

        System.out.println("═══════════════════════════════════════════");
        System.out.println("سناریوی ۵: ترکیب قوانین با and/or");
        System.out.println("═══════════════════════════════════════════");

        // قانون ۴: بالاترین تخفیف بین الکترونیک و بالای ۵۰۰ هزار
        DiscountRule electronicsOrHighValue = electronics.and(highValue);
        // + VIP به صورت جمع
        DiscountRule combined = electronicsOrHighValue.or(vipRule);

        PriceCalculator combinedCalc = new PriceCalculator(
                List.of(combined),
                new SumDiscounts()
        );
        System.out.println("محصول: " + laptop + " | مشتری: " + vip);
        System.out.println(combinedCalc.explain(laptop, vip));


        DiscountRule discountRule = new DeveloperDiscount();
        DiscountRule and = discountRule.and(electronicsOrHighValue);
        PriceCalculator calculator = new PriceCalculator(List.of(and),
                new SumDiscounts()
        );
        System.out.println("محصول: " + laptop + " | مشتری: " + vip1);
        System.out.println(calculator.explain(laptop, vip));

    }
}