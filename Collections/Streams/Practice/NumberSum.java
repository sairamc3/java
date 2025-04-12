import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

class NumberSum {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Given numbers are -> " + numbers);

        Map<Boolean, Integer> map = numbers.stream()
                .collect(
                        Collectors.partitioningBy(n -> (Integer) n % 2 == 0,
                                Collectors.summingInt(Integer::intValue)));

        System.out.println("Even numbers sum is  -> " + map.get(true));
        System.out.println("Odd numbers sum is  -> " + map.get(false));
    }
}