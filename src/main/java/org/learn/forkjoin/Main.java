package org.learn.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;



/**
 * Author: M.R.Khabire
 * Date: 13/08/2026
 * Time: 15:18
 */
public class Main {
    static void main() {
        long l1 = System.nanoTime();
        long l = forkJoinSum(1000000000);
        long l2 = System.nanoTime();
        System.out.println(l+ "----"+ (l2-l1));
    }
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
