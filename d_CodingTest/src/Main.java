import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // 기존 방식
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.println(num);
            }
        }

        // Stream 방식
        numbers.stream()
                .filter(num -> num % 2 == 0) // 짝수 필터링
                .forEach(System.out::println); // 출력
    }
}