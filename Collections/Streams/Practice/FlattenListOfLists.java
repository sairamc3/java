import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenListOfLists {
    public static void main(String[] args) {

        int[][] given = { { 1, 2 }, { 3, 4 }, { 5 } };

        System.out.println("Given matrix array is -> " + Arrays.toString(given));

        List<Integer> result = Arrays.stream(given)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("The result -> " + result);

    }
}
