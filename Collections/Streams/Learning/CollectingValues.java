import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Formatter.BigDecimalLayoutForm;
import java.util.stream.Collectors;

public class CollectingValues {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Beth", 30));
        persons.add(new Person("Eric", 31));
        persons.add(new Person("Deb", 31));
        persons.add(new Person("Liz", 30));
        persons.add(new Person("Wendi", 34));
        persons.add(new Person("Kathy", 35));
        persons.add(new Person("Bert", 32));
        persons.add(new Person("Bill", 34));
        persons.add(new Person("Robert", 38));
        persons.add(new Person("Bill", 40));
        persons.add(new Person("Beth", 45));
        persons.add(new Person("Bert", 38));

        peopleOfAge34(persons);
        peopleOfAge34AsArrayList(persons);
        groupingBasedOnAge(persons);
        findCountBasedOnAge(persons);
        groupingBasedOnAgeAndValueAsNames(persons);
        groupByIfAgeGreaterThan34(persons);
        groupByIfAgeGreaterThan34WithNames(persons);
        findSumOfTheAgesOfPeopleWithSameName(persons);
        findAverageAgeOfPeopleWithSameName(persons);
        findNamesWhoseAgeIsMoreThan34(persons);
        findPersonWithMaxAge(persons);

    }

    private static void peopleOfAge34(List<Person> persons) {

        List<Person> age34 = persons.stream()
                .filter(person -> person.getAge() == 34)
                .collect(Collectors.toList());

        System.out.println("People aged 34 are -> " + age34);
    }

    private static void peopleOfAge34AsArrayList(List<Person> persons) {

        List<Person> age34 = persons.stream()
                .filter(person -> person.getAge() == 34)
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("People aged 34 are with arrayList-> " + age34);

    }

    private static void groupingBasedOnAge(List<Person> persons) {

        Map<Integer, List<Person>> ageGroupedPersons = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        System.out.println("People by age -> " + ageGroupedPersons);
    }

    private static void findCountBasedOnAge(List<Person> persons) {

        Map<Integer, Long> noOfAged = persons.stream()
                .collect(
                        Collectors.groupingBy(Person::getAge,
                                Collectors.counting()));

        System.out.println("Count of  persons by age -> " + noOfAged);
    }

    private static void groupingBasedOnAgeAndValueAsNames(List<Person> persons) {

        Map<Integer, List<String>> namesByAge = persons.stream()
                .collect(
                        Collectors.groupingBy(Person::getAge,
                                Collectors.mapping(Person::getName,
                                        Collectors.toList())));

        System.out.println("Names of persons by age -> " + namesByAge);
    }

    private static void groupByIfAgeGreaterThan34(List<Person> persons) {

        Map<Boolean, List<Person>> personsBy34 = persons.stream()
                .collect(
                        Collectors.partitioningBy(person -> person.getAge() > 34));

        System.out.println("Partitioned based on age 34 -> " + personsBy34);
    }

    private static void groupByIfAgeGreaterThan34WithNames(List<Person> persons) {

        Map<Boolean, List<String>> personsBy34 = persons.stream()
                .collect(
                        Collectors.partitioningBy(person -> person.getAge() > 34,
                                Collectors.mapping(Person::getName, Collectors.toList())));

        System.out.println("Partitioned based on age 34 names -> " + personsBy34);
    }

    private static void findSumOfTheAgesOfPeopleWithSameName(List<Person> persons) {

        Map<String, Integer> sumOfAges = persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getName,
                                Collectors.summingInt(Person::getAge)));

        System.out.println("Sum of age of  people with same name -> " + sumOfAges);
    }

    private static void findAverageAgeOfPeopleWithSameName(List<Person> persons) {

        Map<String, Double> averageOfAges = persons.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getName,
                                Collectors.averagingInt(Person::getAge)));

        System.out.println("Average age of people with same name -> " + averageOfAges);
    }

    private static void findNamesWhoseAgeIsMoreThan34(List<Person> persons) {

        String names = persons.stream()
                .filter(person -> person.getAge() > 34)
                .map(Person::getName)
                .collect(Collectors.joining(","));

        System.out.println("The persons crossing age 34 are -> " + names);
    }

    private static void findPersonWithMaxAge(List<Person> persons) {

        Comparator<Person> byAge = Comparator.comparing(Person::getAge);

        Optional<Person> maxAgePerson = persons.stream()
                .collect(Collectors.maxBy(byAge));

        maxAgePerson.ifPresent(person -> System.out.println("Oldest Person in the list is -> " + person));
    }
}

class Person {

    private String name;
    private Integer age;

    Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "name: " + name + ", age: " + age;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }
}
