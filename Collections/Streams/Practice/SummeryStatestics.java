import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class SummeryStatestics {

    public static void main(String[] args) {
        int[] numbers = { 5, 10, 15, 20, 25 };

        System.out.println("Given array -> " + Arrays.toString(numbers));

        IntSummaryStatistics summary = Arrays.stream(numbers).summaryStatistics();

        System.out.println("Count: " + summary.getCount());
        System.out.println("Sum: " + summary.getSum());
        System.out.println("Max: " + summary.getMax());
        System.out.println("Min: " + summary.getMin());
        System.out.println("Average: " + summary.getAverage());
    }
}
