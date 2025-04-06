import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Searching {
    public static void main(String[] args) {

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("boi", 30, 6));
        dogs.add(new Dog("clover", 35, 12));
        dogs.add(new Dog("aiko", 50, 10));
        dogs.add(new Dog("zooey", 45, 8));
        dogs.add(new Dog("charis", 120, 7));

        checkIfTheElementExists(dogs);
        checkIfAllDogsAreAbove5(dogs);
        checkIfAllDogsAreAbove5WithIntStream(dogs);
        checkIfNoneOfTheDogsWithNameRed(dogs);
        getTheDogWithOver50PoundsAndNameStartsWithC(dogs);
        getAnyDogOfAgeMoreThan5(dogs);
    }

    /**
     * Find if there are any dogs which are weighing over 50 pounds
     * and it's name starts with C
     * 
     * @param dogs
     */
    private static void checkIfTheElementExists(List<Dog> dogs) {

        boolean cNames = dogs.stream()
                .filter(dog -> dog.getWeight() > 50)
                .anyMatch(dog -> dog.getName().startsWith("c"));

        System.out.println(
                "Are there any dogs with weight more than 50 pound and their name starts with 'c' ? " + cNames);

    }

    private static void checkIfAllDogsAreAbove5(List<Dog> dogs) {

        boolean above5 = dogs
                .stream()
                .allMatch(dog -> dog.getAge() > 5);

        System.out.println("All dogs are of age above 5 ? " + above5);
    }

    private static void checkIfAllDogsAreAbove5WithIntStream(List<Dog> dogs) {

        boolean above5 = dogs.stream()
                .mapToInt(Dog::getAge)
                .allMatch(age -> age > 5);

        System.out.println("All dogs are of age above 5 using int stream ? " + above5);
    }

    private static void checkIfNoneOfTheDogsWithNameRed(List<Dog> dogs) {

        boolean redAbsent = dogs.stream()
                .noneMatch(dog -> dog.getName().equals("red"));

        System.out.println("None of the dogs are red: " + redAbsent);
    }

    private static void getTheDogWithOver50PoundsAndNameStartsWithC(List<Dog> dogs) {

        Optional<Dog> cDog = dogs.stream()
                .filter(dog -> dog.getWeight() > 50)
                .filter(dog -> dog.getName().startsWith("c"))
                .findAny();

        cDog.ifPresentOrElse(System.out::println,
                () -> System.out.println("Object not found"));

    }

    private static void getAnyDogOfAgeMoreThan5(List<Dog> dogs) {

        Optional<Dog> majorDog = dogs.stream()
                .filter(dog -> dog.getAge() > 5)
                .findFirst();

        majorDog.ifPresentOrElse(System.out::println,
                () -> System.out.println("Dogs not found with age above 5"));
    }
}

class Dog {
    private String name;
    private int age;
    private int weight;

    public Dog(String name, int weight, int age) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String toString() {
        return "name: " + this.name
                + ", age: " + this.age
                + ", weight: " + this.weight;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getWeight() {
        return this.weight;
    }

}