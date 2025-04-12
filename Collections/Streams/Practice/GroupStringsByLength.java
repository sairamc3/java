import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupStringsByLength {
    public static void main(String[] args) {

        String[] given = { "apple", "bat", "cat", "banana" };

        System.out.println("Given strings -> " + Arrays.toString(given));

        Map<Integer, List<String>> result = Arrays.stream(given)
                .collect(
                        Collectors.groupingBy(String::length, Collectors.toList()));

        System.out.println("Obtained grouping details -> " + result);
    }
}
