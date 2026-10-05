import static java.util.stream.Collectors.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

void main() {
    List<String> list = Arrays.asList("HELLO", "WORLD");
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> squares =
            numbers.stream()
                    .map(n -> n * n)
                    .toList();
//    System.out.println(squares);
    List<String> list1 = list.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().toList();
    /*String r = "MOJIB";
    String[] split = r.split("");
    for (String s : split) {
        System.out.println(s);
    }*/

/*
    List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
    List<Integer> wordLengths = words.stream()
            .map(String::length)
            .collect(toList());
    System.out.println(wordLengths);
*/
/*

    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4);
    List<int[]> pairs =
            numbers1.stream()
                    .flatMap(i -> numbers2.stream()
                            .map(j -> new int[]{i, j})
                    )
                    .collect(toList());
    for (int[] pair : pairs) {
        System.out.print("{");
        for (int i : pair) {
            System.out.print(i);
        }
        System.out.print("");
        System.out.print("}");
        System.out.println();
*/

//    }
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );
    List<Transaction> list2 = transactions.stream()
            .filter(s -> s.getYear() == 2011)
            .sorted(Comparator.comparing(Transaction::getValue))
            .toList();
    List<String> list3 = transactions.stream().map(s -> s.getTrader().getCity())
            .distinct()
            .toList();
    Set<String> list3_1 = transactions.stream()
            .map(s -> s.getTrader().getCity())
            .collect(Collectors.toSet());
/*
    transactions.stream()
//            .map(s -> s.getTrader().getCity())
            .collect(Collectors.toSet()).stream().forEach(System.out::println);
*/

    String traderStr =
            transactions.stream()
                    .map(transaction -> transaction.getTrader().getName())
                    .distinct()
                    .sorted()
                    .reduce("", (n1, n2) -> n1 + " " + n2);
    List<Trader> list4 = transactions.stream().map(Transaction::getTrader)
            .filter(s -> s.getCity().equals("Cambridge"))
            .distinct()
            .sorted(Comparator.comparing(Trader::getName)).toList();
    boolean b = transactions.stream().anyMatch(s -> s.getTrader().getCity().equals(""));
    Integer reduce = transactions.stream().filter(s -> s.getTrader().getCity().equals("")).map(s -> s.getValue()).reduce(0, Integer::sum);
    Optional<Integer> max = transactions.stream().filter(s -> s.getTrader().getCity().equals("")).map(s -> s.getValue()).reduce(Integer::max);
    Optional<Integer> min = transactions.stream().filter(s -> s.getTrader().getCity().equals("")).map(s -> s.getValue()).reduce(Integer::min);
    Optional<Transaction> min1 = transactions.stream().min(Comparator.comparing(Transaction::getValue));
    int sum = transactions.stream().mapToInt(s -> s.getValue()).sum();
    transactions.stream().filter(s -> s.getTrader().getCity().equals("")).map(s -> s.getValue()).forEach(System.out::println);
    String reduce1 = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (n1, n2) -> n1 + n2);
//    System.out.println(reduce1);
    IntStream range = IntStream.rangeClosed(1, 10);
    System.out.println(range);
    Integer[] s= new Integer[]{1,2,3,4,5,6};
    Stream<Integer> stream = Arrays.stream(s);

   /* Stream.iterate(new int[]{0, 1},
                    t -> new int[]{t[1], t[0]+t[1]})

            .limit(20)
            .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

    IntStream.iterate(0, n -> n < 100, n -> n + 4)
            .forEach(System.out::println);
*/
    List<List<Integer>> list5 = List.of(
            List.of(1,2),
            List.of(3,4),
            List.of(5)
    );
/*    list5.stream()
            .map(List::size).forEach(System.out::println);*/
   /* list5.stream()
            .flatMap(List::stream).forEach(System.out::println);
*/    List<Integer> collect = list5.stream()
            .flatMap(List::stream).collect(toList());
//    collect.stream().forEach(System.out::println);

    Integer reduce2 = stream
            .reduce(10, Integer::sum);
//    System.out.println(reduce2);
    Stream.of(1,2,3,4,5)
            .filter(n -> {
                System.out.println("F " + n);
                return n > 2;
            })
            .map(n -> {
                System.out.println("M " + n);
                return n * 2;
            })
            .limit(2)
            .forEach(System.out::println);

    Stream.of(1,2,3,4,5)
            .limit(2)
            .filter(n -> n > 2)
            .forEach(System.err::println);

    Long collect1 = transactions.stream().collect(Collectors.counting());
    Long collect2= transactions.stream().count();
    System.out.println(collect1);
    System.out.println(collect2);
    Comparator<Transaction> transactionComparator=
            Comparator.comparingInt(Transaction::getValue);
    Optional<Transaction> collect3 = transactions.stream().collect(maxBy(transactionComparator));
    System.out.println(transactions.stream().collect(summarizingInt(Transaction::getValue)));
    IntSummaryStatistics collect4 = transactions.stream().collect(summarizingInt(Transaction::getValue));
    System.out.println(transactions.stream().collect(averagingInt(Transaction::getValue)));
    System.out.println(transactions.stream().collect(reducing(1,Transaction::getValue,(i,e) -> i+e)));
    Map<Trader, List<Transaction>> collect5 = transactions.stream().collect(groupingBy(Transaction::getTrader,filtering(transaction -> transaction.getValue()>100,toList())));
    System.out.println(collect5);
    System.out.println(transactions.stream().map(Transaction::getTrader).map(Trader::getName).distinct().collect(joining(",")));

    Stream<Integer> streamTest = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
    List<Integer> numbersTest = stream.reduce(
            new ArrayList<Integer>(),
            (List<Integer> l, Integer e) -> {
                l.add(e);
                return l; },
            (List<Integer> l1, List<Integer> l2) -> {
                l1.addAll(l2);
                return l1; });




}
