package example.bestprise;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author M.R Khabireh
 * Date: 06/09/2026
 * Time: 18:05
 */
public class Shop {
    Random random = new Random();
    private String shopName;
    public Shop(String letsSaveBig) {
        this.shopName = letsSaveBig;
    }

    public String getShopName() {
        return shopName;
    }


    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

/*    public double getPrice(String product) {
        double v = calculatePrice(product);
        System.out.println("from getPrice = "+v);
        return v;
    }*/

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                var price = calculatePrice(product);
                completableFuture.complete(price);
            }catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }).start();
        System.out.println("from getPriceAsync");
        return completableFuture;
    }

    public Future<Double> getPriceAsync1(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

/*    private double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }*/
}
