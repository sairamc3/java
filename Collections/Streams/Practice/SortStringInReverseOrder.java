import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStringInReverseOrder {
    public static void main(String[] args) {
        List < String > colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");

        System.out.println("Given colors are -> " + colors);

        List<String> sortedColors = colors.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

        System.out.println("Sorted Colors in reverse order are -> " + sortedColors);
    }
}
