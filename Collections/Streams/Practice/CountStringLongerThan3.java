import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountStringLongerThan3 {

    public static void main(String[] args) {

        String[] given = { "a", "ab", "abc", "abcd", "abcde" };

        System.out.println("Given array -> " + Arrays.toString(given));

        List<String> filtered = Arrays.stream(given)
                .filter(element -> element.length() > 3)
                .collect(Collectors.toList());

        System.out.println("The string with length more than 3 is -> " + filtered);
    }
}
