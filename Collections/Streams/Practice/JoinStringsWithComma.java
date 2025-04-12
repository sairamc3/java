import java.util.Arrays;
import java.util.stream.Collectors;

public class JoinStringsWithComma {
    public static void main(String[] args) {
        String[] given = { "Java", "Python", "Go" };

        System.out.println("Given String array is -> " + Arrays.toString(given));

        String afterJoining = Arrays.stream(given)
                .collect(Collectors.joining(", "));

        System.out.println("After joining with comma -> " + afterJoining);
    }
}
