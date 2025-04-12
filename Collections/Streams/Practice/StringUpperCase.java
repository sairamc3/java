import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUpperCase {
    public static void main(String[] args) {

        List<String> given = Arrays.asList("apple", "banana", "cherry");

        System.out.println("Given String array -> " + given);

        List<String> upper = given.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Upper case -> " + upper);
    }

}
