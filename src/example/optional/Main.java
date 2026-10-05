package example.optional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author M.R Khabireh
 * Date: 18/08/2026
 * Time: 15:21
 */
public class Main {
    static void main() {

        List<String> sentences = List.of("hello world", "java stream api");
        Stream<String[]> result = sentences.stream()
                .map(s -> s.split(" "));
        List<String[]> collect = sentences.stream()
                .map(s -> s.split(" ")).collect(Collectors.toList());


    }
}
