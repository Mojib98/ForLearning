package example;

import java.util.Arrays;

/**
 * @author M.R Khabireh
 * Date: 15/08/2026
 * Time: 10:53
 */
public class Test {
    static void main() {
        int[] ones = new int[10];
        Arrays.fill(ones, 1);
        Arrays.parallelPrefix(ones, (a, b) -> a + b);
        int[] numbers = {2, 3, 4, 5};

        Arrays.parallelPrefix(numbers, (a, b) -> a + b);
        for (int one : numbers) {
            System.out.println(one);
        }
    }
}
