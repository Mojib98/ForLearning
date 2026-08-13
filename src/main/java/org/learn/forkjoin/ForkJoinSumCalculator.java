package org.learn.forkjoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Author: M.R.Khabire
 * Date: 13/08/2026
 * Time: 15:09
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <=THRESHOLD){
            return computeSequentially();
        }
        var left = new ForkJoinSumCalculator(numbers,start,start+length/2);
        ForkJoinTask<Long> fork = left.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+length/2,end);
        Long rightResult = rightTask.compute();
        Long join = fork.join();
        return join+rightResult;

    }
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
