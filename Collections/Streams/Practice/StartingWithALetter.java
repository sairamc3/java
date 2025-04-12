import java.util.Arrays;
import java.util.List;

public class StartingWithALetter {
    public static void main(String[] args) {
        List < String > colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");

        System.out.println("Given list -> " + colors);
        String letter = "B";

        long count = colors.stream().filter(color -> color.startsWith(letter)).count();

        System.out.println("The number of colors starting with letter B are -> " + count);


    }
}
