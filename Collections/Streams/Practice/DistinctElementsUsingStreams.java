import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctElementsUsingStreams {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 23, 22, 23, 24, 24, 33, 15, 26, 15);

        System.out.println("The given numbers are -> " + nums);

        List<Integer> unique = nums.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("The unique numbers in the list are -> " + unique);
    }
}
