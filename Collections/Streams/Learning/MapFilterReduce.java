import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class MapFilterReduce {

    public static void main(String[] args) {

        // Create an list of integers
        // Find the squers
        // Check the ones having value greater than 20
        // Count the final records
        System.out.println("Find Squres greater than 20 ");
        squersGreaterThan20Count();

        System.out.println("----------------------------");

        System.out.println("Find Average of the readings");

        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(2021, 2, 30, 405.91));
        readings.add(new Reading(2021, 2, 30, 405.98));
        readings.add(new Reading(2021, 2, 30, 406.14));
        readings.add(new Reading(2021, 2, 30, 406.48));
        readings.add(new Reading(2021, 2, 30, 406.20));
        readings.add(new Reading(2021, 2, 30, 407.12));
        readings.add(new Reading(2021, 2, 30, 406.03));

        findAverageOfTheReadings(readings);
        findMaxOfTheReadingss(readings);
        findSumOfTheReadings(readings);
        findSumUsingReduceOperator(readings);
    }

    private static void squersGreaterThan20Count() {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println(nums);

        long result = nums.stream()
                .map(n -> n * n)
                .filter(n -> n > 20)
                .count();

        System.out.println("The count of the numbers whose square is greater than 20 -> " + result);
    }

    /**
     * Use streams to find the average of the readings whose values are b/w 406 and
     * 407
     */
    private static void findAverageOfTheReadings(List<Reading> readings) {

        DoubleStream valueStream = readings.stream()
                .mapToDouble(reading -> reading.getValue());

        OptionalDouble average = valueStream
                .filter(value -> value > 406 && value < 407)
                .average();

        if (average.isPresent()) {
            System.out.println("The average value is -> " + average.getAsDouble());
        } else {
            System.out.println("There are no readings between 406 and 407");
        }
    }

    private static void findMaxOfTheReadingss(List<Reading> readings) {

        OptionalDouble max = readings
                .stream()
                .mapToDouble(Reading::getValue)
                .max();
        if (max.isPresent()) {
            System.out.println("Max Value is -> " + max.getAsDouble());
        } else {
            System.out.println("The readings list is empty");
        }

    }

    private static void findSumOfTheReadings(List<Reading> readings) {

        double sum = readings
                .stream()
                .mapToDouble(Reading::getValue)
                .sum();

        System.out.println("The sum of all the values is -> " + sum);
    }

    private static void findSumUsingReduceOperator(List<Reading> readings) {

        OptionalDouble sum = readings
                .stream()
                .mapToDouble(Reading::getValue)
                .reduce((a, b) -> a + b);

        System.out.println("Sum using reduce method -> " + sum.getAsDouble());

    }

}

class Reading {
    int year, month, day;
    double value;

    Reading(int year, int month, int day, double value) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}
