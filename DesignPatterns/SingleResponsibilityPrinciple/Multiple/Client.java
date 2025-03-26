import java.util.Random;

class Client {
    public static void main(String[] args) {
        System.out.println("A demo without Single Responsibility Principle");
        System.out.println("----------------------------------------------");

        Employee robin = new Employee("Robin", "Smith", 7.5);
        showEmpDetail(robin);

        System.out.println("\n********\n");

        Employee kevin = new Employee("Kevin", "Proctor", 2.5);
        showEmpDetail(kevin);

    }

    private static void showEmpDetail(Employee emp) {
        emp.displayEmpDetail();
        System.out.println("The emp Id: " + emp.generateEmpId(emp.firstName));
        System.out.println("The employee is a " + emp.checkSeniority(emp.experienceInYears));
    }
}

class Employee {
    public String firstName, lastName, empId;
    public double experienceInYears;

    public Employee(String firstName, String lastName, double exp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experienceInYears = exp;
    }

    public void displayEmpDetail() {
        System.out.println("Employee Name: " + this.firstName + " " + this.lastName);
        System.out.println("The Employee has " + this.experienceInYears + " years of experience");
    }

    public String checkSeniority(double exp) {
        return exp > 5 ? "Senior" : "Junior";
    }

    public String generateEmpId(String empFirstName) {
        int random = new Random().nextInt(1000);
        empId = empFirstName.substring(0, 1) + random;
        return empId;
    }
}