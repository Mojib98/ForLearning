package example.bestprise;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author M.R Khabireh
 * Date: 06/09/2026
 * Time: 18:05
 */
public class Main {
    static void main() {
       /* Shop shop = new Shop();
        Future<Double> priceAsync = shop.getPriceAsync("99");

        try {
            shop.getPrice("11");
            shop.getPrice("11");
            shop.getPrice("11");
            System.out.println(priceAsync.get());
            shop.getPrice("11");
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (ExecutionException e) {
            System.out.println(e);
        }*/

        List<Shop> shops = List.of(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));
//        List<String> list = shops.stream().map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice("22"))).toList();
        long start = System.nanoTime();
        System.out.println(shops.stream().map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice("22"))).toList());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
