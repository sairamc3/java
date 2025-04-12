import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
    public static void main(String[] args) {

        String given = "hello world hello java";

        System.out.println("Given String is -> " + given);

        Map<String, Long> resultMap = Arrays.stream(given.split(" "))
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("The word count is -> " + resultMap);

    }
}
