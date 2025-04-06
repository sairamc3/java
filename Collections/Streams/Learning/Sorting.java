import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Sorting {

    public static void main(String[] args) {

        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Jerry", "Yellow", 3));
        ducks.add(new Duck("George", "Brown", 4));
        ducks.add(new Duck("Kramer", "Mottled", 6));
        ducks.add(new Duck("Elaine", "White", 2));

        simpleSorting();
        sortingCustomObjects(ducks);
        sortingWithCustomComparator(ducks);
        sortingWithCompartorVariable(ducks);
        getDistinctElements(ducks);
    }

    private static void simpleSorting() {

        System.out.println("--------------------");
        System.out.println("Sorting a simple String array");
        System.out.println("--------------------");

        Stream.of("Jerry", "George", "Kramer", "Elaine")
                .sorted()
                .forEach(System.out::println);
    }

    private static void sortingCustomObjects(List<Duck> ducks) {

        System.out.println("--------------------");
        System.out.println("Sorting Custom objects");
        System.out.println("--------------------");

        ducks.stream()
                .sorted()
                .forEach(System.out::println);
    }

    private static void sortingWithCustomComparator(List<Duck> ducks) {


        System.out.println("--------------------");
        System.out.println("sortingWithCustomComparator");
        System.out.println("--------------------");

        ducks.stream()
                .sorted((duck1, duck2) -> duck1.age - duck2.age)
                .forEach(System.out::println);
    }

    private static void sortingWithCompartorVariable(List<Duck> ducks) {

        System.out.println("--------------------");
        System.out.println("sortingWithCompartorVariable");
        System.out.println("--------------------");

        Comparator<Duck> byAge = Comparator.comparing(Duck::getAge);
        Comparator<Duck> byColor = Comparator.comparing(Duck::getColor);
        Comparator<Duck> byName = Comparator.comparing(Duck::getName);

        ducks.stream()
                .sorted(byAge)
                .forEach(System.out::println);

        ducks.stream()
                .sorted(byColor)
                .forEach(System.out::println);

        ducks.stream()
                .sorted(byName)
                .forEach(System.out::println);

    }

    private static void getDistinctElements(List<Duck> ducks) {


        System.out.println("--------------------");
        System.out.println("getDistinctElements");
        System.out.println("--------------------");

        ducks.stream()
                .map(Duck::getColor)
                .distinct()
                .forEach(System.out::println);
    }
}

class Duck implements Comparable<Duck> {
    String name;
    String color;
    int age;

    Duck(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    @Override
    public int compareTo(Duck duck) {
        return this.name.compareTo(duck.name);
    }

    public String toString() {
        return "name: " + name + ", color: " + color + ", age: " + age;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getAge() {
        return this.age;
    }
}
