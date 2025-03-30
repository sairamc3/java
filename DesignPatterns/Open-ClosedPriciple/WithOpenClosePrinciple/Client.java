import java.util.Arrays;
import java.util.List;

class Client {
    public static void main(String[] args) {

        System.out.println("*** A demo with OCP ***");

        ScienceDistinctionDecoder scienceDistinctionDecoder = new ScienceDistinctionDecoder();
        ArtsDistinctionDecoder artsDistinctionDecoder = new ArtsDistinctionDecoder();

        List<Student> students = enrollStudents();

        students.forEach(System.out::println);

        for (Student student : students) {
            if (student instanceof ScienceStudent) {
                scienceDistinctionDecoder.evaluateDistinction(student);
            } else if (student instanceof ArtsStuddent) {
                artsDistinctionDecoder.evaluateDistinction(student);
            }
        }

    }

    private static List<Student> enrollStudents() {

        Student sam = new ScienceStudent("Sam", "R1", 81.5, "Comp.Sc.");
        Student bob = new ScienceStudent("Bob", "R2", 72, "Physics");
        Student john = new ArtsStuddent("John", "R3", 71, "History");
        Student kate = new ArtsStuddent("Kate", "R4", 66.5, "English");

        List<Student> students = Arrays.asList(sam, bob, john, kate);

        return students;
    }
}

abstract class Student {
    String name;
    String regNumber;
    String department;
    double score;

    public Student(String name, String regNumber, double score, String department) {
        this.name = name;
        this.regNumber = regNumber;
        this.score = score;
        this.department = department;
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

class ScienceStudent extends Student {

    ScienceStudent(String name, String regNumber, double score, String department) {
        super(name, regNumber, score, department);
    }
}

class ArtsStuddent extends Student {

    ArtsStuddent(String name, String regNumber, double score, String department) {
        super(name, regNumber, score, department);
    }
}

interface DistinctionDecoder {
    void evaluateDistinction(Student student);
}

class ScienceDistinctionDecoder implements DistinctionDecoder {

    @Override
    public void evaluateDistinction(Student student) {
        if (student.score > 80)
            System.out.println(student.regNumber + " has received a distinction in science.");
    }

}

class ArtsDistinctionDecoder implements DistinctionDecoder {

    @Override
    public void evaluateDistinction(Student student) {
        if (student.score > 70)
            System.out.println(student.regNumber + " has received a distinction in science.");
    }

}
