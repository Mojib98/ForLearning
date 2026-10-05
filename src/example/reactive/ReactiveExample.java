package example.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author M.R Khabireh
 * Date: 15/09/2026
 * Time: 09:20
 */
public class ReactiveExample {
    public static void main(String[] args) throws InterruptedException {

        // 1. ساخت Publisher (منتشرکننده داده)
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2. ساخت Subscriber (مصرف‌کننده داده)
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("✅ Subscribed!");
                // درخواست اولین آیتم (Backpressure)
                subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("📥 Received: " + item);
                // شبیه‌سازی پردازش زمان‌بر
                try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {}
                // درخواست آیتم بعدی
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                    System.out.println("🔁 Retry " + this);
                    publisher.subscribe(this); // دوباره وصل شو

                    System.err.println("❌ Giving up: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("🏁 Done!");
            }
        };

        // 3. اتصال Subscriber به Publisher
        publisher.subscribe(subscriber);

        // 4. انتشار داده‌ها (1 تا 5)
        for (int i = 1; i <= 1000; i++) {
            System.out.println("📤 Publishing: " + i);
            publisher.submit(i);
            TimeUnit.MILLISECONDS.sleep(100); // فاصله بین انتشار
            if (i == 50){
                throw new RuntimeException();
            }
        }

        // 5. بستن Publisher (اعلام پایان)
        publisher.close();

        // 6. صبر برای اتمام پردازش
        TimeUnit.SECONDS.sleep(2);
    }
}
