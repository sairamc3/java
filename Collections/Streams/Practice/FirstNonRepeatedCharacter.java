import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedCharacter {
    public static void main(String[] args) {
        String given = "swiss";

        System.out.println("Given String -> " + given);

        Optional<Character> firstChar = given.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.groupingBy(Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findAny();

        firstChar.ifPresentOrElse(
                c -> System.out.println("First non repeated character is -> " + c),
                () -> System.out.println("There is no non repeated character in the string"));
    }
}
