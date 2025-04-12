import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SecondSmallestSecondLargest {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 17, 54, 14, 14, 33, 45, -11);

        System.out.println("given list of numbers -> " + nums);

        Integer secondSmallest = nums.stream()
                .sorted()
                .skip(1)
                .findFirst()
                .orElse(null);

        Integer secondLargest = nums.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);

        System.out.println("Second Smallest number is -> " + secondSmallest
                + ", Second Largest number is -> " + secondLargest);
    }
}
