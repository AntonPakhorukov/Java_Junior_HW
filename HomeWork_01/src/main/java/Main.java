import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. Напишите программу, которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех четных чисел в списке.
 * 2. * Дополнительная задача:
 * Переработать метод балансировки корзины товаров cardBalancing() с использованием Stream API
 */

public class Main {
    public static void main(String[] args) {
        /**
         * Version.1
         */
        System.out.printf("Version.1: %.2f\n",Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 2 == 0)
                .mapToDouble(i -> i)
                .average()
                .getAsDouble());

        /**
         * Version.2
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        System.out.printf("Version.2: %.2f\n", list.stream().filter(i -> i % 2 == 0).mapToDouble(i -> i).average().getAsDouble());
    }
}
