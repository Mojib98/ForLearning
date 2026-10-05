package example;

/**
 * @author M.R Khabireh
 * Date: 03/08/2026
 * Time: 18:29
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Menu {

    public static List<Dish> menu() {
        return List.of(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),

                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),

                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }
    public enum CaloricLevel { DIET, NORMAL, FAT }

    static void main() {
        List<Dish> ll3 = menu();
        boolean b = ll3.removeIf(s -> s.getType().equals(Dish.Type.OTHER));
        ll3.forEach(System.out::println);
        Map<Dish.Type, List<Dish>> collect = menu().stream().collect(groupingBy(Dish::getType));
        Map<CaloricLevel, List<Dish>> collect1 = menu().stream().collect(groupingBy(
                dish -> {
                    if (dish.getCalories() <= 400)
                        return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700)
                        return CaloricLevel.FAT;
                    else
                        return CaloricLevel.NORMAL;
                }
        ));
        collect1.forEach((type,dish) ->
                System.out.println(type +" : "+dish));


        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu().stream().filter(dish -> dish.getCalories() > 500)
                        .collect(groupingBy(Dish::getType));

//    print(caloricDishesByType);
        /*
        collect.forEach((type, dishes) ->
                System.out.println(type +" : "+dishes));*/

        Map<String, Integer> map = new HashMap<>();
        map.put("Java", 10);

        map.merge("Java", 5, (oldValue, newValue) -> {
            System.out.println("old = " + oldValue);
            System.out.println("new = " + newValue);

            return oldValue + newValue;
        });
        System.out.println(map);
    }
    public static void print(Map<Dish.Type,List<Object>> map){
        map.forEach((type,dish) ->
                System.out.println(type +" : "+dish));

    }

}