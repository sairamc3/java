import java.util.Arrays;
import java.util.List;

class Client {
    public static void main(String[] args) {
        System.out.println("*** A demo without OCP");

        List<Student> enrolledStudents = enrollStudents();

        // Display all the results
        System.out.println("===Results:===");
        for (Student student : enrolledStudents) {
            System.out.println(student);
        }

        System.out.println("===Distinctions:===");
        DistinctionDecoder distinctionDecoder = new DistinctionDecoder();

        for (Student student : enrolledStudents) {
            distinctionDecoder.evaluateDistinction(student);
        }

    }

    private static List<Student> enrollStudents() {

        Student sam = new Student("Sam", "R1", 81.5, "Comp.Sc.");
        Student bob = new Student("Bob", "R2", 72, "Physics");
        Student john = new Student("John", "R3", 71, "History");
        Student kate = new Student("Kate", "R4", 66.5, "English");

        List<Student> students = Arrays.asList(sam, bob, john, kate);

        return students;
    }
}

class Student {
    String name;
    String regNumber;
    String department;
    double score;

    public Student(String name, String regNumber, double score, String department) {
        this.name = name;
        this.regNumber = regNumber;
        this.department = department;
        this.score = score;
    }

    @Override
    public String toString() {
        return ("Name: " + name +
                "\n Reg Number: " + regNumber +
                "\n Dept:" + department +
                "\n Marks:" + score +
                "\n******");
    }
}

class DistinctionDecoder {

    List<String> science = Arrays.asList("Comp.Sc.", "Physics");
    List<String> arts = Arrays.asList("History", "English");

    public void evaluateDistinction(Student student) {

        if (science.contains(student.department)) {
            if (student.score > 80)
                System.out.println(student.regNumber + " has received a distinction in science.");
        }
        if (arts.contains(student.department)) {
            if (student.score > 70)
                System.out.println(student.regNumber + " has received a distinction in arts. ");
        }

    }
}