import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class UniqueValuesCount {

    public static void main(String[] args) {
        String[] values = { "One", "Two", "One", "Ten", "Three", "Five", "Three" };

        Map<String, Long> unique = Arrays.stream(values)
                .collect(Collectors.groupingBy(value -> value, Collectors.counting()));

        System.out.println("Given String array is -> " + Arrays.toString(values));

        System.out.println("The count of unique values are -> " + unique);
    }
}
